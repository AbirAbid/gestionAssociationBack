package com.proxym.pfe.gestionAssociationBack.missionBenevole.restcontrollers;

import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.MissionBenevole;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.services.MissionBenevoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MissionBenevoleRestControllers {
    @Autowired
    MissionBenevoleService missionBenevoleService;


    @RequestMapping(value = "/listMissionBenevole", method = RequestMethod.GET)
    public List<MissionBenevole> getListMission() {
        try {
            return missionBenevoleService.findAllMissionService();
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/listMissionBenevoleEvent", method = RequestMethod.GET)
    public List<MissionBenevole> getListMissionByEvent(Long id) {
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
}
