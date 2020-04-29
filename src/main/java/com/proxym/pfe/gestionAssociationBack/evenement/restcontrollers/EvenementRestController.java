package com.proxym.pfe.gestionAssociationBack.evenement.restcontrollers;

import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.evenement.services.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
