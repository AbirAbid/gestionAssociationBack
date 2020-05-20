package com.proxym.pfe.gestionAssociationBack.biens.restControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proxym.pfe.gestionAssociationBack.biens.dto.ParticiperBienFormDto;
import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.biens.entities.UserBien;
import com.proxym.pfe.gestionAssociationBack.biens.repositories.BienRepositories;
import com.proxym.pfe.gestionAssociationBack.biens.services.BienService;
import com.proxym.pfe.gestionAssociationBack.evenement.services.EvenementService;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.dto.ParticiperMissionDto;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.Mission;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.UserMission;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import com.proxym.pfe.gestionAssociationBack.user.repositories.UserRepository;
import com.proxym.pfe.gestionAssociationBack.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class BienRestController {
    @Autowired
    BienService bienService;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BienRepositories bienRepositories;
    @Autowired
    EvenementService evenementService;

    /***List Biens***/

    @RequestMapping(value = "/listBien", method = RequestMethod.GET)
    public List<Bien> getListBien() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            List<Bien> biens = bienService.findAllService();
            List<Bien> biens1 = new ArrayList<>();
            String jsonInString;

            for (int i = 0; i < biens.size(); i++) {
                jsonInString = mapper.writeValueAsString(biens.get(i));
                System.out.println("jsonInString" + jsonInString);
                Bien bien = mapper.readValue(jsonInString, Bien.class);

                System.out.println(mapper.writeValueAsString(biens.get(i).getId()));
                biens1.add(bien);
            }

            return biens1;
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    /***List Biens par event id***/


    @RequestMapping(value = "/listBienEvent/{id}", method = RequestMethod.GET)
    public List<Bien> getListBienByEvent(@PathVariable("id") Long id) {

        try {
            List<Bien> biens = bienService.findAllByEventService(id);
            ObjectMapper mapper = new ObjectMapper();
            List<Bien> biens1 = new ArrayList<>();
            String jsonInString;

            for (int i = 0; i < biens.size(); i++) {
                //Convert  bien to JSON format,
                jsonInString = mapper.writeValueAsString(biens.get(i));

                System.out.println("jsonInString" + jsonInString);
                //get Bien sous forme json
                Bien bien = mapper.readValue(mapper.writeValueAsString(biens.get(i)), Bien.class);

                // System.out.println(mapper.writeValueAsString(biens.get(i).getId()));
                biens1.add(bien);
            }
            return biens1;
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

    @RequestMapping(value = "/donnerBien/{username}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    public void donnerBien(@RequestBody ParticiperBienFormDto participerBienFormDto,
                           @PathVariable String username) {
        try {
            User user = userService.findUserByUsernameService(username);
            Bien bien = participerBienFormDto.getBien();
            UserBien userBien = new UserBien();
            int qteDonnee = participerBienFormDto.getQteDonnee();
            System.out.println("qteDonnee****" + qteDonnee);

            List<UserBien> userBiens = user.getUserBiens();
            List<Long> bienUserId = new ArrayList<>();

            for (int i = 0; i < userBiens.size(); i++) {
                bienUserId.add(userBiens.get(i).getBien().getId());
                System.out.println(userBiens.get(i).getBien());
            }
            /** IF  exist USER AND BIEN***/
            if (bienUserId.contains(bien.getId())) {
                int index = bienUserId.indexOf(bien.getId());
                System.out.println("index****" + index);
                user.getUserBiens().get(index).setQteDonnee(user.getUserBiens().get(index).getQteDonnee() + qteDonnee);
                bien.setTotaleqteDonnee(bien.getTotaleqteDonnee() + qteDonnee);
                bienService.saveBienService(bien);
                userRepository.save(user);
            } else {
                bien.getEvenement().setActive(1);
                userBien.setUser(user);
                userBien.setBien(bien);
                userBien.setQteDonnee(5);
                userBien.setDateParticipation(new Date());
                user.getUserBiens().add(userBien);
                bien.setTotaleqteDonnee(bien.getTotaleqteDonnee() + qteDonnee);
                evenementService.addEventService(bien.getEvenement());
                bienService.saveBienService(bien);
                userRepository.save(user);


                System.out.println("***** user.getUserBiens().get(0).getAffected()********" + user.getUserBiens().get(0).getQteDonnee());
            }
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
        }
    }

    /****  Get list Bien by user *****/

    @RequestMapping(value = "/listBienByUser/{username}", method = RequestMethod.GET)
    public List<UserBien> getListBienByUser(@PathVariable("username") String username) {
        try {
            User user = userService.findUserByUsernameService(username);
            List<UserBien> userBiens = user.getUserBiens();
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString;

            List<UserBien> userBiens1 = new ArrayList<>();
            for (int i = 0; i < userBiens.size(); i++) {
                jsonInString = mapper.writeValueAsString(userBiens.get(i).getBien());

                //get Bien sous forme json
                UserBien userBien = mapper.readValue(mapper.writeValueAsString(userBiens.get(i)), UserBien.class);

                userBiens1.add(userBien);


            }
            return userBiens1;

        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());
            return null;
        }

    }


    @RequestMapping(value = "/listUser", method = RequestMethod.GET)
    public List<User> getListUser() {
        try {
            return userRepository.findAll();
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/listUserBien", method = RequestMethod.GET)
    // public List<UserBien> getListUserBien() {
    public List<UserBien> getListUserBien() {
        try {
            List<User> users = userService.getAllDonneursService();
            List<UserBien> userBiens = new ArrayList<>();
            for (int i = 0; i < users.size(); i++) {
                for (int j = 0; j < users.get(i).getUserBiens().size(); j++) {
                    userBiens.add(users.get(i).getUserBiens().get(j));
                }
            }
            for (int i = 0; i < userBiens.size(); i++) {
                System.out.println(" userBiens.get(i).getBien()  " + userBiens.get(i).getBien());
            }


            return userBiens;
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }


}
