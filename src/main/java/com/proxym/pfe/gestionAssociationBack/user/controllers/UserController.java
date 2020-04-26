package com.proxym.pfe.gestionAssociationBack.user.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/")
    public String root() {
        System.out.println("index sans Auth");
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


}
