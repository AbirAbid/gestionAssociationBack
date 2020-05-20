package com.proxym.pfe.gestionAssociationBack.biens.controllers;

import com.proxym.pfe.gestionAssociationBack.biens.entities.UserBien;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.UserMission;
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
@RequestMapping("/bien/")

public class BienController {
    @Autowired
    UserService userService;


    @RequestMapping(value = "/listDonneurs")
    public String listDonneurs(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {

        try {

            List<User> users = userService.getAllDonneursService();
            /**eliminate duplicate value list users****/
            Set<User> set = new HashSet<>(users);
            users.clear();
            users.addAll(set);
            /**End eliminate duplicate value list users****/

            Page<UserBien> userBiens = getListAllDonneurs(page);
            /* for (int i = 0; i < users.size(); i++) {
             *//**** to get All UserBien ****//*
                for (int j = 0; j < users.get(i).getUserBiens().size(); j++) {
                    userBiens.add(users.get(i).getUserBiens().get(j));
                }
            }*/

            int pagesCount = userBiens.getTotalPages();
            int[] pages = new int[pagesCount];

            for (int i = 0; i < pagesCount; i++) {
                pages[i] = i;
                System.out.println(" pages[i] " + pages[i]);
            }
            model.addAttribute("pageCourante", page);
            model.addAttribute("pageContent", userBiens.getContent());
            model.addAttribute("userBiens", userBiens);
            model.addAttribute("pages", pages);


            return "donneurs/list_donneurs";
        } catch (Exception e) {
            return "pagesError/error";
        }

    }


    Page<UserBien> getListAllDonneurs(int page) {
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
        Pageable pageable = new PageRequest(page, 10);
        if (pageable.getOffset() >= userBiens.size()) {
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = (int) ((pageable.getOffset() + pageable.getPageSize()) > userBiens.size() ?
                userBiens.size() :
                pageable.getOffset() + pageable.getPageSize());
        Page<UserBien> userBiens1 = new PageImpl<>(userBiens.subList(startIndex, endIndex), pageable, userBiens.size());
        return userBiens1;
    }

}
