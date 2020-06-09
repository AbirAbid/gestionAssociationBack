package com.proxym.pfe.gestionAssociationBack.evenement.restcontrollers;

import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.evenement.dto.EventCountCategories;
import com.proxym.pfe.gestionAssociationBack.evenement.dto.EventCountElmtsDto;
import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.evenement.repositories.EventRepositories;
import com.proxym.pfe.gestionAssociationBack.evenement.services.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class EvenementRestController {
    @Autowired
    EvenementService evenementService;


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


}
