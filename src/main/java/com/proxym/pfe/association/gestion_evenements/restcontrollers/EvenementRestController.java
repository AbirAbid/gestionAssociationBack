package com.proxym.pfe.association.gestion_evenements.restcontrollers;

import com.proxym.pfe.association.gestion_evenements.dto.EventCountCategories;
import com.proxym.pfe.association.gestion_evenements.dto.EventCountElmtsDto;
import com.proxym.pfe.association.gestion_evenements.dto.EventParticipCount;
import com.proxym.pfe.association.gestion_evenements.entities.Evenement;
import com.proxym.pfe.association.gestion_evenements.services.EvenementService;
import com.proxym.pfe.association.gestion_missions.services.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class EvenementRestController {
    @Autowired
    EvenementService evenementService;

    @Autowired
    MissionService missionService;

    @RequestMapping(value = "/listEvent", method = RequestMethod.GET)
    public List<Evenement> getListEvent() {
        try {
            return evenementService.listEventService();
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
    public Optional<Evenement> getEventById(@PathVariable("id") long id) {
        try {
            return evenementService.findEventByIdService(id);
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/countElements", method = RequestMethod.GET)
    public EventCountElmtsDto getCount() {
        try {
            return evenementService.getElementNumber();
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/countEventCategories", method = RequestMethod.GET)
    public List<EventCountCategories> getCountCateg() {
        try {
            List<EventCountCategories> eventCountCategories = evenementService.countCategEvent();

            return eventCountCategories;
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    /***List Events by categorie***/

    @RequestMapping(value = "/eventByCatg/{categorie}", method = RequestMethod.GET)
    public List<Evenement> getEventByCategorie(@PathVariable("categorie") String categorie) {
        try {
            List<Evenement> evenements = evenementService.findAllByCategorieService(categorie);

            return evenements;
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    /***get nb prticipants by  event**/
    @RequestMapping(value = "/countParticiByEvent", method = RequestMethod.GET)
    public List<EventParticipCount> countPartByEvent() {
        try {

            List<EventParticipCount> eventParticipCounts = evenementService.countPartByEvent();

            return eventParticipCounts;
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }
    /***get nb prticipants by categorie event**/

    @RequestMapping(value = "/countParticiByCatEvent", method = RequestMethod.GET)
    public List<EventCountCategories> countPartByCategorieEvent() {
        try {

            List<EventCountCategories> eventParticipCounts = evenementService.countPartByCategorieEvent();

            return eventParticipCounts;
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }
}
