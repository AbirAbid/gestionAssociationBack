package com.proxym.pfe.gestionAssociationBack.evenement.restcontrollers;

import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.evenement.services.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
