package com.proxym.pfe.gestionAssociationBack.biens.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/bien/")

public class BienController {
   /* @Autowired
    ParticiperBienService participerBienService;


    Display List donneurs

    @RequestMapping(value = "/listDonneurs")
    public String listDonneurs(Model model) {

        try {
            List<ParticiperBien> participerBiens = participerBienService.findAllParticipationBienService();

            model.addAttribute("participerBiens", participerBiens);

            return "donneurs/list_donneurs";
        } catch (Exception e) {
            return "pagesError/error";
        }


    } **/
}
