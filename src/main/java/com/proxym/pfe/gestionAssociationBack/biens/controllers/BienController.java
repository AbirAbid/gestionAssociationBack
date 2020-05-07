package com.proxym.pfe.gestionAssociationBack.biens.controllers;

import com.proxym.pfe.gestionAssociationBack.biens.entities.UserBien;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import com.proxym.pfe.gestionAssociationBack.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/bien/")

public class BienController {
    @Autowired
    UserService userService;


    @RequestMapping(value = "/listDonneurs")
    public String listDonneurs(Model model) {

        try {

            List<User> users = userService.getAllDonneursService();
            /**eliminate duplicate value list users****/
            Set<User> set = new HashSet<>(users);
            users.clear();
            users.addAll(set);
            /**End eliminate duplicate value list users****/

            List<UserBien> userBiens = new ArrayList<>();
            for (int i = 0; i < users.size(); i++) {
                /**** to get All UserBien ****/
                for (int j = 0; j < users.get(i).getUserBiens().size(); j++) {
                    userBiens.add(users.get(i).getUserBiens().get(j));
                }
            }
            model.addAttribute("userBiens", userBiens);

            return "donneurs/list_donneurs";
        } catch (Exception e) {
            return "pagesError/error";
        }


    }
}
