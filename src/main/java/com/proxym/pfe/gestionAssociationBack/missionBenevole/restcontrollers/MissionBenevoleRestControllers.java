package com.proxym.pfe.gestionAssociationBack.missionBenevole.restcontrollers;

import com.proxym.pfe.gestionAssociationBack.missionBenevole.dto.ParticiperMissionDto;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.Mission;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.MissionBenevole;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.UserMission;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.repositories.MissionRepository;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.services.MissionBenevoleService;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.services.MissionService;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.services.ParticiperMissionBenevoleService;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import com.proxym.pfe.gestionAssociationBack.user.repositories.UserRepository;
import com.proxym.pfe.gestionAssociationBack.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sun.nio.cs.US_ASCII;

import java.util.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MissionBenevoleRestControllers {
    @Autowired
    MissionBenevoleService missionBenevoleService;
    @Autowired
    UserService userService;
    @Autowired
    ParticiperMissionBenevoleService participerMissionBenevoleService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MissionService missionService;
    @Autowired
    MissionRepository missionRepository;

    @RequestMapping(value = "/listMissionBenevole", method = RequestMethod.GET)
    public List<MissionBenevole> getListMission() {
        try {
            return missionBenevoleService.findAllMissionService();
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/listMissionBenevoleEvent/{id}", method = RequestMethod.GET)
    public List<MissionBenevole> getListMissionByEvent(@PathVariable("id") Long id) {
        try {
            return missionBenevoleService.findAllMissionByEventService(id);
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/listMissionBenevoleRegion/{ville}", method = RequestMethod.GET)
    public List<MissionBenevole> getMissionByRegion(@PathVariable("ville") String ville) {
        try {
            return missionBenevoleService.findAllByEvenement_VilleService(ville);
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    /**
     * Participer mission Api
     **/


    @RequestMapping(value = "/participerMission/{username}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    public void donnerBien(@RequestBody ParticiperMissionDto participerMissionDto,
                           @PathVariable String username) {
        try {
            UserMission userMission = new UserMission();

            User user = userService.findUserByUsernameService(username);


            System.out.println("******************** participerMissionDto *******************" + participerMissionDto);
            System.out.println("******************** user *******************" + user);

            System.out.println("******************** user *******************" + user);
            userMission.setUser(user);
            Mission mission = participerMissionDto.getMission();

            userMission.setMission(mission);
            System.out.println("******************** Mission********************" + mission);

            userMission.setAffected(0);
            userMission.setEnAttente(1);
            userMission.setDemandeDate(new Date());
            System.out.println("******************** userMission *******************" + userMission);
            user.getUserMissions().add(userMission);
            System.out.println("**************  user.getUserMissions().size()**********" + user.getUserMissions().size());
            //missionService.saveMissionService(mission);
            missionRepository.save(mission);
            userRepository.save(user);


            System.out.println("***** user.getUserMissions().get(0).getAffected()********" + user.getUserMissions().get(0).getAffected());

        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
        }
    }


    @RequestMapping(value = "/listMission", method = RequestMethod.GET)
    public List<Mission> getallMission() {
        try {//System.out.println(" missionRepository.findAll()***"+ missionRepository.findAll());
            return missionService.findAllMissionService();
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }
}
