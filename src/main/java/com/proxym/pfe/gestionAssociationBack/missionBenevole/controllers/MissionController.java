package com.proxym.pfe.gestionAssociationBack.missionBenevole.controllers;

import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.Mission;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.UserMission;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.services.MissionService;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import com.proxym.pfe.gestionAssociationBack.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


    @RequestMapping(value = "/listbenevoles")
    public String listBenevoles(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {

       // try {
            Page<UserMission> userMissions = getListAllPartiMission(page);

            int pagesCount = userMissions.getTotalPages();
            int[] pages = new int[pagesCount];

            for (int i = 0; i < pagesCount; i++) {
                pages[i] = i;
                System.out.println(" pages[i] " + pages[i]);
            }
            model.addAttribute("pageCourante", page);
            model.addAttribute("pageContent", userMissions.getContent());
            model.addAttribute("userMissions", userMissions);
            model.addAttribute("pages", pages);


            return "benevoles/liste-benevoles";
    /*    } catch (Exception e) {
            return "pagesError/error";
        }*/


    }


    @RequestMapping(value = "/affectMission")

    public String affectMission(Model model, String id, Long id2, @RequestParam(name = "page", defaultValue = "0") int page){
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
            Page<UserMission> userMissions1 = getListAllPartiMission(page);
            model.addAttribute("userMissions", userMissions1);


            return "benevoles/liste-benevoles";
        } catch (Exception e) {
            return "pagesError/error";
        }
    }


    Page<UserMission> getListAllPartiMission(int page) {
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
         Page<UserMission> userMissions1 = new PageImpl<>(userMissions,new PageRequest(page, 2),userMissions.size());

        return  userMissions1;
    }
}
