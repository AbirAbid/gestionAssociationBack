package com.proxym.pfe.gestionAssociationBack.user.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String root() {
        System.out.println("index sans Auth");
        return "index";
    }

    @GetMapping("/login")
    // @PreAuthorize("hasRole('ADMIN')")

    public String login(Model model) {
        try {

            return "authAdmin/login";
        } catch (Exception e) {
            System.out.println("exception " + e);
            return "pagesError/error";

        }

    }


}
