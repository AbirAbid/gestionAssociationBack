package com.proxym.pfe.association.gestion_biens.restControllers;

import com.proxym.pfe.association.gestion_biens.models.dto.ParticiperBienFormDto;
import com.proxym.pfe.association.gestion_biens.models.entities.Bien;
import com.proxym.pfe.association.gestion_biens.models.services.BienService;
import com.proxym.pfe.association.gestion_evenements.services.EvenementService;
import com.proxym.pfe.association.gestion_utilisateurs.repositories.UserRepository;
import com.proxym.pfe.association.gestion_biens.models.entities.UserBien;
import com.proxym.pfe.association.gestion_utilisateurs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
//@RequestMapping("/api/biens/")

public class BienRestController {
    @Autowired
    BienService bienService;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    EvenementService evenementService;

    /***List Biens***/

    @RequestMapping(value = "/listBien", method = RequestMethod.GET)
    public List<Bien> getListBien() {
        try {
            return bienService.getListBien();
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    /***List Biens par event id***/


    @RequestMapping(value = "/listBienEvent/{id}", method = RequestMethod.GET)
    public List<Bien> getListBienByEvent(@PathVariable("id") Long id) {

        try {
            return bienService.findAllByEventServiceRest(id);
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    /***List Biens par region***/
    @RequestMapping(value = "/listBienRegion/{ville}", method = RequestMethod.GET)
    public List<Bien> getBienByRegion(@PathVariable("ville") String ville) {
        try {
            return bienService.getBienByRegionRest(ville);
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }


    /***Donner Bien***/
    @RequestMapping(value = "/apiMembreAuthoriser/donnerBien/{username}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void donnerBien(@RequestBody ParticiperBienFormDto participerBienFormDto,
                           @PathVariable String username) {
        try {
            bienService.donnerBienSerice(participerBienFormDto, username);
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
        }
    }

    /****  Get list Bien by user *****/

    @RequestMapping(value = "/apiMembreAuthoriser/listBienByUser/{username}", method = RequestMethod.GET)
    public List<UserBien> getListBienByUser(@PathVariable("username") String username) {
        try {


            return bienService.getListBienByUserRest(username);

        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());
            return null;
        }

    }



}
