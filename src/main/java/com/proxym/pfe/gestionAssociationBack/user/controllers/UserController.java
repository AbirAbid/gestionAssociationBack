package com.proxym.pfe.gestionAssociationBack.user.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model) {


        return "pagesError/403";
    }

}
