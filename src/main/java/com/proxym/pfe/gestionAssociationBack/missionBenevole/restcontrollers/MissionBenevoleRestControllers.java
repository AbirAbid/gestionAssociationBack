package com.proxym.pfe.gestionAssociationBack.missionBenevole.restcontrollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.biens.entities.UserBien;
import com.proxym.pfe.gestionAssociationBack.evenement.services.EvenementService;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.dto.ParticiperMissionDto;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.Mission;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.MissionUserDisplay;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.UserMission;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.repositories.MissionRepository;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.services.MissionService;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import com.proxym.pfe.gestionAssociationBack.user.repositories.UserRepository;
import com.proxym.pfe.gestionAssociationBack.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MissionBenevoleRestControllers {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    MissionService missionService;
    @Autowired
    MissionRepository missionRepository;
    @Autowired
    EvenementService evenementService;


    @RequestMapping(value = "/listMissionBenevoleEvent/{id}", method = RequestMethod.GET)
    public List<Mission> getListMissionByEvent(@PathVariable("id") Long id) {
        try {
            return missionService.findAllMissionByEventService(id);
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/listMissionBenevoleRegion/{ville}", method = RequestMethod.GET)
    public List<Mission> getMissionByRegion(@PathVariable("ville") String ville) {
        try {
            return missionService.findAllByEvenement_VilleService(ville);
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    /**
     * Participer mission Api
     **/


    @RequestMapping(value = "/participerMission/{username}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    public void participerMission(@RequestBody ParticiperMissionDto participerMissionDto,
                                  @PathVariable String username) {
        try {
            UserMission userMission = new UserMission();

            User user = userService.findUserByUsernameService(username);

            System.out.println("******************** participerMissionDto *******************" + participerMissionDto);
            System.out.println("******************** user *******************" + user);

            // System.out.println("******************** user *******************" + user);
            userMission.setUser(user);
            Mission mission = participerMissionDto.getMission();
            mission.getEvenement().setActive(1);
            userMission.setMission(mission);
            userMission.setAffected(0);
            userMission.setEnAttente(1);
            userMission.setDemandeDate(participerMissionDto.getDemandeDate());
            System.out.println("******************** userMission *******************" + userMission);
            user.getUserMissions().add(userMission);
            System.out.println("**************  user.getUserMissions().size()**********" + user.getUserMissions().size());
            //missionService.saveMissionService(mission);
            evenementService.addEventService(mission.getEvenement());
            missionRepository.save(mission);
            userRepository.save(user);


            System.out.println("***** user.getUserMissions().get(0).getAffected()********" + user.getUserMissions().get(0).getAffected());

        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
        }
    }


    @RequestMapping(value = "/listMission", method = RequestMethod.GET)
    public List<Mission> getallMission() {
        try {
            return missionService.findAllMissionService();
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/listMissionByUser/{username}", method = RequestMethod.GET)
    public List<UserMission> getallMissionUser(@PathVariable String username) {
        try {

            return getListMissionUser(username);

        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }


    @RequestMapping(value = "/listMissionDisplay/{username}/{id}", method = RequestMethod.GET)
    public List<MissionUserDisplay> getallMissionDisplayUser(@PathVariable String username, @PathVariable Long id) {
        try {

            return getListMissionUserDisplay(username, id);

        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }


    /***methode get allParicipation By user****/
    List<UserMission> getListMissionUser(String username) throws IOException {

        User user = userService.findUserByUsernameService(username);
        List<UserMission> userMissions = user.getUserMissions();
        ObjectMapper mapper = new ObjectMapper();

        List<UserMission> userMissions1 = new ArrayList<>();
        for (int i = 0; i < userMissions.size(); i++) {
            System.out.println("userMissions" + userMissions.get(i).getMission().getTitre());

            UserMission userMission = mapper.readValue(mapper.writeValueAsString(userMissions.get(i)), UserMission.class);

            userMissions1.add(userMission);


        }
        return userMissions1;


    }

    /***methode get MissionUserDisplay FOR user ByEvent****/

    List<MissionUserDisplay> getListMissionUserDisplay(String username, Long id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<UserMission> userMissions = getUserMissionByEvent(username, id);
        List<Mission> missions = missionService.findAllMissionByEventService(id);


        List<MissionUserDisplay> missionUserDisplays = new ArrayList<>();

//pour éliminer redondance
        for (int i = 0; i < missions.size(); i++) {
            MissionUserDisplay missionUserDisplay = new MissionUserDisplay();
            missionUserDisplay.setMission(missions.get(i));
            for (int j = 0; j < userMissions.size(); j++) {
                boolean exist = userMissions.get(j).getMission().getId() == missions.get(i).getId();
                if (exist) {


                    missionUserDisplay.setExist(1);
                    break;

                } else {
                    missionUserDisplay.setExist(0);

                }

            }
            missionUserDisplays.add(missionUserDisplay);



        }


        List<MissionUserDisplay> missionUserDisplays2 = new ArrayList<>();
        for (int i = 0; i < missionUserDisplays.size(); i++) {

            MissionUserDisplay missionUserDisplay = mapper.readValue(mapper.writeValueAsString(missionUserDisplays.get(i)), MissionUserDisplay.class);

            missionUserDisplays2.add(missionUserDisplay);


        }

        return missionUserDisplays2;
    }

    /**************Methode  get UseMission By event id ***/
    List<UserMission> getUserMissionByEvent(String username, Long id) throws IOException {

        List<UserMission> userMissions = getListMissionUser(username);
        List<UserMission> userMissionsByIdEvt = new ArrayList<>();
        for (int i = 0; i < userMissions.size(); i++) {
            if (userMissions.get(i).getMission().getEvenement().getId() == id) {
                userMissionsByIdEvt.add(userMissions.get(i));
            }
        }
        return userMissionsByIdEvt;
    }

}
