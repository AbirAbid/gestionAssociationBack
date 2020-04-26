package com.proxym.pfe.gestionAssociationBack.sponsors.restcontrollers;

import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.sponsors.entities.Sponsor;
import com.proxym.pfe.gestionAssociationBack.sponsors.services.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

public class SponsorRestController {
    @Autowired
    SponsorService sponsorService;

    @RequestMapping(value = "/listSponsors", method = RequestMethod.GET)
    public List<Sponsor> getListSponsor() {
        try {
            return sponsorService.findAllSponsorServ();
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }
}
