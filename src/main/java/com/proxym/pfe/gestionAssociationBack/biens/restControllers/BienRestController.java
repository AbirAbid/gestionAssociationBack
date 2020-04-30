package com.proxym.pfe.gestionAssociationBack.biens.restControllers;

import com.proxym.pfe.gestionAssociationBack.biens.dto.ParticiperBienFormDto;
import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.biens.entities.ParticiperBien;
import com.proxym.pfe.gestionAssociationBack.biens.services.BienService;
import com.proxym.pfe.gestionAssociationBack.biens.services.ParticiperBienService;
import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import com.proxym.pfe.gestionAssociationBack.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Bien donnerBien(@RequestBody ParticiperBienFormDto participerBienFormDto,
                           @PathVariable String username) {
        try {
            System.out.println("/donnerBien/{username}");

            User user = userService.findUserByUsernameService(username);
            Set<ParticiperBien> setparticiperBiens = new HashSet<>();
            participerBienFormDto.getParticiperBien().setUser(user);
            setparticiperBiens.add(participerBienService.saveParticipationBienService(participerBienFormDto.getParticiperBien()));
            participerBienFormDto.getBien().setParticiperBiens(setparticiperBiens);


            return bienService.saveBienService(participerBienFormDto.getBien());
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

}
