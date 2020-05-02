package com.proxym.pfe.gestionAssociationBack.missionBenevole.restcontrollers;

import com.proxym.pfe.gestionAssociationBack.biens.dto.ParticiperBienFormDto;
import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.biens.entities.ParticiperBien;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.dto.ParticiperMissionDto;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.MissionBenevole;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.ParticiperMissionBenevole;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.services.MissionBenevoleService;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.services.ParticiperMissionBenevoleService;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import com.proxym.pfe.gestionAssociationBack.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MissionBenevoleRestControllers {
    @Autowired
    MissionBenevoleService missionBenevoleService;
    @Autowired
    UserService userService;
    @Autowired
    ParticiperMissionBenevoleService participerMissionBenevoleService;

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

    @RequestMapping(value = "/participerMission/{username}", method = RequestMethod.POST)
    public ParticiperMissionBenevole donnerBien(@RequestBody ParticiperMissionDto participerMissionDto,
                                                @PathVariable String username) {
        try {

            ParticiperMissionBenevole participerMission = new ParticiperMissionBenevole();
            User user = userService.findUserByUsernameService(username);
            MissionBenevole missionBenevole = participerMissionDto.getMissionBenevole();
            Date dateD = participerMissionDto.getDateD();
            Boolean exist = participerMissionBenevoleService.existMissionUserService(missionBenevole, user);
            System.out.println("exist " + exist);
            participerMission.setAffected(0);
            participerMission.setDateDemande(dateD);
            participerMission.setUser(user);
            participerMission.setEnAttente(1);

            participerMission.setMissionBenevole(missionBenevole);
            participerMissionBenevoleService.saveParticipationMissionService(participerMission);
          /*  Set<ParticiperMissionBenevole> set = new HashSet<>();
            set.add(participerMission);
            missionBenevole.setParticiperMissionBenevoles(set);
            missionBenevoleService.saveMissionService(missionBenevole);*/


            return participerMission;
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }


}
