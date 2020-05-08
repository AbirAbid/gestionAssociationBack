package com.proxym.pfe.gestionAssociationBack.missionBenevole.controllers;

import com.proxym.pfe.gestionAssociationBack.biens.entities.UserBien;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.UserMission;
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
@RequestMapping("/mission/")
public class MissionController {
    @Autowired
    UserService userService;


    @RequestMapping(value = "/listbenevoles")
    public String listBenevoles(Model model) {

        try {

            List<User> users = userService.getAllBenevolesService();
            /**eliminate duplicate value list users****/
            Set<User> set = new HashSet<>(users);
            users.clear();
            users.addAll(set);
            /**End eliminate duplicate value list users****/

            List<UserMission> userMissions = new ArrayList<>();
            for (int i = 0; i < users.size(); i++) {
                /**** to get All UserBien ****/
                for (int j = 0; j < users.get(i).getUserMissions().size(); j++) {
                    userMissions.add(users.get(i).getUserMissions().get(j));
                }
            }
            model.addAttribute("userMissions", userMissions);

            return "benevoles/liste-benevoles";
        } catch (Exception e) {
            return "pagesError/error";
        }


    }
}
