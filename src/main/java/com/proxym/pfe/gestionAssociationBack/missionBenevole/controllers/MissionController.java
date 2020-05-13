package com.proxym.pfe.gestionAssociationBack.missionBenevole.controllers;

import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.Mission;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.UserMission;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.services.MissionService;
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
    @Autowired
    MissionService missionService;
    int index = 0;


    @RequestMapping(value = "/listbenevoles")
    public String listBenevoles(Model model) {

        try {
            List<UserMission> userMissions = getListAllPartiMission();


            model.addAttribute("userMissions", userMissions);

            return "benevoles/liste-benevoles";
        } catch (Exception e) {
            return "pagesError/error";
        }


    }


    @RequestMapping(value = "/affectMission")

    public String affectMission(Model model, String id, Long id2) {
        try {
            User user = userService.findUserByUsernameService(id);
            Mission mission = missionService.findMissionByIdService(id2);
            System.out.println("user.nom: " + user.getNom());
            System.out.println("mission.titre: " + mission.getTitre());
            int index = 0;

            List<UserMission> userMissions = user.getUserMissions();

            while (true) {
                if (userMissions.get(index).getMission().getId() == mission.getId() && index < userMissions.size()) {
                    break;
                } else {
                    index++;
                }
                System.out.println(" index: " + index);

            }

            System.out.println(" index: after****** " + index);

            userMissions.get(index).setAffected(1);
            System.out.println("userMissions.get(index): " + userMissions.get(index).getMission());

            userService.saveUserService(user);
            List<UserMission> userMissions1 = getListAllPartiMission();
            model.addAttribute("userMissions", userMissions1);


            return "benevoles/liste-benevoles";
        } catch (Exception e) {
            return "pagesError/error";
        }
    }


    List<UserMission> getListAllPartiMission() {
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
        return userMissions;
    }
}
