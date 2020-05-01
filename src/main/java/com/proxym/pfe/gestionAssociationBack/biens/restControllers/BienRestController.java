package com.proxym.pfe.gestionAssociationBack.biens.restControllers;

import com.proxym.pfe.gestionAssociationBack.biens.dto.ParticiperBienFormDto;
import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.biens.entities.ParticiperBien;
import com.proxym.pfe.gestionAssociationBack.biens.repositories.BienRepositories;
import com.proxym.pfe.gestionAssociationBack.biens.repositories.ParticiperBienRepositories;
import com.proxym.pfe.gestionAssociationBack.biens.services.BienService;
import com.proxym.pfe.gestionAssociationBack.biens.services.ParticiperBienService;
import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import com.proxym.pfe.gestionAssociationBack.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class BienRestController {
    @Autowired
    BienService bienService;
    @Autowired
    UserService userService;
    @Autowired
    ParticiperBienService participerBienService;
    @Autowired
    BienRepositories bienRepositories;
    @Autowired
    ParticiperBienRepositories participerBienRepositories;

    /***List Biens***/

    @RequestMapping(value = "/listBien", method = RequestMethod.GET)
    public List<Bien> getListBien() {
        try {
            return bienService.findAllService();
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    /***List Biens par event id***/

    @RequestMapping(value = "/listBienEvent/{id}", method = RequestMethod.GET)
    public List<Bien> getListBienByEvent(@PathVariable("id") Long id) {
        try {
            return bienService.findAllByEventService(id);
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    /***List Biens par region***/
    @RequestMapping(value = "/listBienRegion/{ville}", method = RequestMethod.GET)
    public List<Bien> getBienByRegion(@PathVariable("ville") String ville) {
        try {
            return bienService.findAllByEvenement_VilleService(ville);
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    /***Donner Bien***/

    @RequestMapping(value = "/donnerBien/{username}", method = RequestMethod.POST)
    // public Bien donnerBien(@RequestBody Bien bien,
    public ParticiperBien donnerBien(@RequestBody ParticiperBienFormDto participerBienFormDto,
                                     @PathVariable String username) {
        try {
            ParticiperBien participerBien = new ParticiperBien();
            participerBien.setBien(participerBienFormDto.getBien());
            participerBien.setUser(userService.findUserByUsernameService(username));
            participerBien.setQteDonnee(participerBienFormDto.getQteDon());

            System.out.println(participerBien.getQteDonnee());
            System.out.println(participerBienService.saveParticipationBienService(participerBien));
            System.out.println("after" + participerBien.getQteDonnee());



          /*  System.out.println("/donnerBien/{username}");

            User user = userService.findUserByUsernameService(username);
            Set<ParticiperBien> setparticiperBiens = new HashSet<>();
            System.out.println("participerBienFormDto.getParticiperBien(): " + participerBienFormDto.getParticiperBien());

            participerBienFormDto.getParticiperBien().setUser(user);
            ParticiperBien participerBien = participerBienService.saveParticipationBienService(participerBienFormDto.getParticiperBien());

            setparticiperBiens.add(participerBien);
            //participerBienFormDto.getBien().setParticiperBiens(setparticiperBiens);


            /***Calcule qte donnée ***/

            //participerBienFormDto.getBien().setTotaleqteDonnee(participerBienFormDto.getBien().getTotaleqteDonnee() + participerBienFormDto.getParticiperBien().getQteDonnee());

            return participerBien;
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    /**
     * getAllDonByUser
     **/

    @RequestMapping(value = "/listDonBienUsername/{username}", method = RequestMethod.GET)
    public List<ParticiperBien> getDonBienByuser(@PathVariable("username") String username) {
        try {
            // System.out.println(participerBienService.findAllParticipationBienService());

            List<ParticiperBien> participerBiens = participerBienService.findAllByUser_UsernameService(username);
            System.out.println("participerBiens**" + participerBiens);
            //  System.out.println(bienRepositories.findByParticiperBiens((long) 5));


            return participerBiens;
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }


}
