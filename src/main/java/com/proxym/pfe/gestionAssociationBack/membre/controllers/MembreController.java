package com.proxym.pfe.gestionAssociationBack.membre.controllers;

import com.proxym.pfe.gestionAssociationBack.biens.entities.UserBien;
import com.proxym.pfe.gestionAssociationBack.membre.entities.MailToSend;
import com.proxym.pfe.gestionAssociationBack.membre.services.MembreService;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.UserMission;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/membres/")
public class MembreController {
    @Autowired
    MembreService membreService;
    @Autowired
    public JavaMailSender emailSender;

    @GetMapping(value = "listeMembres")
    public String showList(Model model) {
        try {

            List<User> users = membreService.getAllMembreService();

            model.addAttribute("pagesMembres", users);


            return "membres/membresListe";
        } catch (Exception e) {
            System.out.println(e);
            return "pagesError/error";
        }


    }

    @RequestMapping(value = "/membreDetailUrl",method = RequestMethod.GET)
    public String membreDetail(Model model, String id) {

        try {
            User user = membreService.getOneMembreService(id);
            List<UserBien> userBiens = user.getUserBiens();
            List<UserMission> userMissions = user.getUserMissions();

            model.addAttribute("userMissions", userMissions);
            model.addAttribute("userBiens", userBiens);
            model.addAttribute("membre", user);

            return "membres/membreDetail";
        } catch (Exception e) {
            return "pagesError/error";
        }


    }


    /***Send mail***/

    @RequestMapping(value = "/sendmailUrL",method = RequestMethod.GET)
    public String sendMail(Model model, String id) {

        try {
            MailToSend mailToSend = new MailToSend();
            mailToSend.setReceiver(id);
            model.addAttribute("mailSend", mailToSend);

            return "membres/membreSendEmail";
        } catch (Exception e) {
            return "pagesError/error";
        }


    }


    @RequestMapping(value = "/sendHtmlEmailUrl",method = RequestMethod.POST)
    public String sendHtmlEmail(MailToSend mailToSend, RedirectAttributes redirectAttributes) {
        try {
            membreService.sendMailMembre(mailToSend);
            redirectAttributes.addFlashAttribute("sendMailMessage", " Votre message a été envoyé avec succès ");

            return "redirect:/membres/listeMembres";
        } catch (Exception e) {
            System.out.println("Exception  " + e);
            return "pagesError/error";
        }

    }


}
