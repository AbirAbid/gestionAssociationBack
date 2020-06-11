package com.proxym.pfe.gestionAssociationBack.user.controllers;

import com.proxym.pfe.gestionAssociationBack.biens.entities.UserBien;
import com.proxym.pfe.gestionAssociationBack.biens.services.BienService;
import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.evenement.services.EvenementService;
import com.proxym.pfe.gestionAssociationBack.membre.services.MembreService;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.UserMission;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.services.MissionService;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import com.proxym.pfe.gestionAssociationBack.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    MembreService membreService;
    @Autowired
    BienService bienService;
    @Autowired
    MissionService missionService;
    @Autowired
    EvenementService evenementService;

    @GetMapping("/")
    public String root(Model model) {
        List<User> users = membreService.getAllMembreService();
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
