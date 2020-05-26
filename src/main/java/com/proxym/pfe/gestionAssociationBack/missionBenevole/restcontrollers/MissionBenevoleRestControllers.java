package com.proxym.pfe.gestionAssociationBack.missionBenevole.restcontrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
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

            User user = userService.findUserByUsernameService(username);

            missionService.participerMissionRest(participerMissionDto, user);
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
        }
    }


    @RequestMapping(value = "/listMission", method = RequestMethod.GET)
    public List<Mission> getallMission() {
        try {
            List<Mission> missions = missionService.findAllMissionService();
            ObjectMapper mapper = new ObjectMapper();
            List<Mission> missions2 = new ArrayList<>();
            for (int i = 0; i < missions.size(); i++) {

                Mission missionUserDisplay = mapper.readValue(mapper.writeValueAsString(missions.get(i)), Mission.class);

                missions2.add(missionUserDisplay);


            }
            return missions2;
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/listMissionByUser/{username}", method = RequestMethod.GET)
    public List<UserMission> getallMissionUser(@PathVariable String username) {
        try {

            return missionService.getListMissionUserRest(username);

        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }


    @RequestMapping(value = "/listMissionDisplay/{username}/{id}", method = RequestMethod.GET)
    public List<MissionUserDisplay> getallMissionDisplayUser(@PathVariable String username, @PathVariable Long id) {
        try {

            return missionService.getListMissionUserDisplayRest(username, id);

        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }


    @RequestMapping(value = "/annulerDemande/{username}", method = RequestMethod.POST)
    public void libererMission(@PathVariable String username, @RequestBody Long id) {

        missionService.libererMissionRest(username, id);

    }


}
