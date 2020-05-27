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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


    @RequestMapping(value = "/affectMissionUrl")

    public String affectMission(Model model, String username, Long id, @RequestParam(name = "page", defaultValue = "0") int page,
                                RedirectAttributes redirectAttributes) {
        try {
            User user = userService.findUserByUsernameService(username);
            Mission mission = missionService.findMissionByIdService(id);

            missionService.affecterMission(user, mission);
            Page<UserMission> userMissions1 = getListAllPartiMission(page);

            int pagesCount = userMissions1.getTotalPages();
            int[] pages = new int[pagesCount];

            for (int i = 0; i < pagesCount; i++) {
                pages[i] = i;
                System.out.println(" pages[i] " + pages[i]);
            }
            model.addAttribute("pageCourante", page);
            model.addAttribute("pageContent", userMissions1.getContent());
            model.addAttribute("pages", pages);
            model.addAttribute("userMissions", userMissions1);
            redirectAttributes.addFlashAttribute("message", "Vous avez affecter " + user.getNom() + " à " + mission.getTitre());

            return "redirect:/mission/listbenevoles";
        } catch (Exception e) {
            return "pagesError/error";
        }
    }

    /******************************Liberer Missio *********************************************/
    @RequestMapping(value = "/libererMissionUrl")

    public String libererMission(Model model, String username, Long id, @RequestParam(name = "page", defaultValue = "0") int page,
                                 RedirectAttributes redirectAttributes) {
        try {

            User user = userService.findUserByUsernameService(username);
            Mission mission = missionService.findMissionByIdService(id);
            missionService.libererMission(user, mission);

            Page<UserMission> userMissions1 = getListAllPartiMission(page);

            int pagesCount = userMissions1.getTotalPages();
            int[] pages = new int[pagesCount];

            for (int i = 0; i < pagesCount; i++) {
                pages[i] = i;
                System.out.println(" pages[i] " + pages[i]);
            }
            model.addAttribute("pageCourante", page);
            model.addAttribute("pageContent", userMissions1.getContent());
            model.addAttribute("pages", pages);
            model.addAttribute("userMissions", userMissions1);
            redirectAttributes.addFlashAttribute("messageFree", "Vous avez libérer " + user.getNom());

            return "redirect:/mission/listbenevoles";
        } catch (Exception e) {
            return "pagesError/error";
        }
    }

    /****************************getLIstMission Methode****************************/
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
        Pageable pageable = new PageRequest(page, 10);
        if (pageable.getOffset() >= userMissions.size()) {
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = (int) ((pageable.getOffset() + pageable.getPageSize()) > userMissions.size() ?
                userMissions.size() :
                pageable.getOffset() + pageable.getPageSize());
        Page<UserMission> userMissions1 = new PageImpl<>(userMissions.subList(startIndex, endIndex), pageable, userMissions.size());
        return userMissions1;
    }


}
