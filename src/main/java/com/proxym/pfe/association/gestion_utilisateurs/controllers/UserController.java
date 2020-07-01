package com.proxym.pfe.association.gestion_utilisateurs.controllers;

import com.proxym.pfe.association.gestion_biens.services.BienService;
import com.proxym.pfe.association.gestion_evenements.entities.Evenement;
import com.proxym.pfe.association.gestion_evenements.services.EvenementService;
import com.proxym.pfe.association.gestion_missions.services.MissionService;
import com.proxym.pfe.association.gestion_utilisateurs.entities.User;
import com.proxym.pfe.association.gestion_utilisateurs.services.UserService;
import com.proxym.pfe.association.gestion_biens.entities.UserBien;
import com.proxym.pfe.association.gestion_missions.entities.UserMission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {


    @Autowired
    BienService bienService;
    @Autowired
    MissionService missionService;
    @Autowired
    EvenementService evenementService;
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String root(Model model) {
        List<User> users = userService.getAllMembreService();
        List<UserBien> userBiens = bienService.getListUserBien();
        List<Evenement> evenementsList = evenementService.listEventService();
        List<UserMission> userMissions = missionService.getListMissionUser();


        model.addAttribute("nbMembre", users.size());
        model.addAttribute("nbEvent", evenementsList.size());
        model.addAttribute("nbDonneur", userBiens.size());
        model.addAttribute("nbBenevole", userMissions.size());


        return "index";
    }

    @GetMapping("/login")

    public String login(Model model) {
        try {

            return "authAdmin/login";
        } catch (Exception e) {
            System.out.println("exception " + e);
            return "pagesError/error";

        }

    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model) {


        return "pagesError/403";
    }

}
