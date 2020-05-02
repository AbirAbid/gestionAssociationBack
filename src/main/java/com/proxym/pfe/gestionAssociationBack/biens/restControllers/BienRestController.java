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
    public ParticiperBien donnerBien(@RequestBody ParticiperBienFormDto participerBienFormDto,
                                     @PathVariable String username) {
        try {

            ParticiperBien participerBien = new ParticiperBien();
            User user = userService.findUserByUsernameService(username);
            Bien bien = participerBienFormDto.getBien();
            int qteD = participerBienFormDto.getQteDon();
            /**Update qtedonnee  bien **/
            System.out.println("bien***" + bien.getId());
            System.out.println("bien.getTotaleqteDonnee()" + bien.getTotaleqteDonnee());
            bien.setTotaleqteDonnee(bien.getTotaleqteDonnee() + qteD);
            bien.getEvenement().setActive(1);
            bienService.saveBienService(bien);
            /**End Update qtedonnee  bien**/
            Boolean exist = participerBienService.existBienUserService(bien, user);
            System.out.println("exist " + exist);
            if (exist == false) {
                participerBien.setBien(bien);
                participerBien.setUser(user);
                participerBien.setQteDonnee(qteD);
                participerBien.setDateParticipation(participerBienFormDto.getDatePart());
                System.out.println("participerBien" + participerBien);

                participerBienService.saveParticipationBienService(participerBien);


            } else {
                participerBien = participerBienService.findByBienAndUserService(bien, user);
                participerBien.setQteDonnee(participerBien.getQteDonnee() + qteD);
                participerBien.setDateParticipation(participerBienFormDto.getDatePart());

                participerBienService.saveParticipationBienService(participerBien);

                System.out.println("findByBienAndUserService  " + participerBienService.findByBienAndUserService(bien, user));


            }

            return participerBien;
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    /**
     * getAllBienByUser
     **/

    @RequestMapping(value = "/listDonBienUsername/{username}", method = RequestMethod.GET)
    public List<ParticiperBien> getDonBienByuser(@PathVariable("username") String username) {
        try {

            List<ParticiperBien> participerBiens = participerBienService.findAllByUser_UsernameService(username);
            System.out.println("participerBiens**" + participerBiens);


            return participerBiens;
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }


}
