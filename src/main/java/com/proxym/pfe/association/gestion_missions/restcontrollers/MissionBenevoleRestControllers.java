package com.proxym.pfe.association.gestion_missions.restcontrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proxym.pfe.association.gestion_evenements.services.EvenementService;
import com.proxym.pfe.association.gestion_missions.entities.MissionUserDisplay;
import com.proxym.pfe.association.gestion_missions.services.MissionService;
import com.proxym.pfe.association.gestion_utilisateurs.entities.User;
import com.proxym.pfe.association.gestion_utilisateurs.repositories.UserRepository;
import com.proxym.pfe.association.gestion_utilisateurs.services.UserService;
import com.proxym.pfe.association.gestion_missions.dto.ParticiperMissionDto;
import com.proxym.pfe.association.gestion_missions.entities.Mission;
import com.proxym.pfe.association.gestion_missions.entities.UserMission;
import com.proxym.pfe.association.gestion_missions.repositories.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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


    /**
     * Participer mission Api
     **/

   @RequestMapping(value = "/apiMembreAuthoriser/participerMission/{username}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    public void participerMission(@RequestBody ParticiperMissionDto participerMissionDto,
                                  @PathVariable String username) {
        try {

            User user = userService.findUserByUsernameService(username);

            missionService.participerMissionRest(participerMissionDto, user);
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
        }
    }

    /**
     * list mission by username Api
     **/

    @RequestMapping(value = "/apiMembreAuthoriser/listMissionByUser/{username}", method = RequestMethod.GET)
    public List<UserMission> getallMissionUser(@PathVariable String username) {
        try {

            return missionService.getListMissionUserRest(username);

        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    /**
     * list mission by username  and event id Api
     ***/
    @RequestMapping(value = "/apiMembreAuthoriser/listMissionDisplay/{username}/{id}", method = RequestMethod.GET)
    public List<MissionUserDisplay> getallMissionDisplayUser(@PathVariable String username, @PathVariable Long id) {
        try {

            return missionService.getListMissionUserDisplayRest(username, id);

        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    /**
     * delete participation mission
     **/


    @RequestMapping(value = "/apiMembreAuthoriser/annulerDemande/{username}", method = RequestMethod.POST)
    public void libererMission(@PathVariable String username, @RequestBody Long id) {

        missionService.libererMissionRest(username, id);

    }


}
