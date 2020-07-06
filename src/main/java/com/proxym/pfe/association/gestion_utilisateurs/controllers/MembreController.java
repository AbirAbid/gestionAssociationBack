package com.proxym.pfe.association.gestion_utilisateurs.controllers;

import com.proxym.pfe.association.gestion_evenements.services.EvenementService;
import com.proxym.pfe.association.gestion_biens.models.entities.UserBien;
import com.proxym.pfe.association.gestion_missions.entities.UserMission;
import com.proxym.pfe.association.gestion_utilisateurs.dto.MailToSend;
import com.proxym.pfe.association.gestion_utilisateurs.entities.User;
import com.proxym.pfe.association.gestion_utilisateurs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/membres/")
public class MembreController {

    @Autowired
    UserService userService;
    @Autowired
    public JavaMailSender emailSender;
    @Autowired
    EvenementService evenementService;


    @GetMapping(value = "listeMembres")
    public String showList(Model model) {
        try {

            List<User> users = userService.getAllMembreService();

            model.addAttribute("pagesMembres", users);


            return "membres/membresListe";
        } catch (Exception e) {
            System.out.println(e);
            return "pagesError/error";
        }


    }

    @RequestMapping(value = "/membreDetailUrl", method = RequestMethod.GET)
    public String membreDetail(Model model, String id) {

        try {
            User user = userService.getOneMembreService(id);
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

    @RequestMapping(value = "/sendmailUrL", method = RequestMethod.GET)
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


    @RequestMapping(value = "/sendHtmlEmailUrl", method = RequestMethod.POST)
    public String sendHtmlEmail(MailToSend mailToSend, RedirectAttributes redirectAttributes) {
        try {
            userService.sendMailMembre(mailToSend);
            redirectAttributes.addFlashAttribute("sendMailMessage", " Votre message a été envoyé avec succès ");

            return "redirect:/membres/listeMembres";
        } catch (Exception e) {
            System.out.println("Exception  " + e);
            return "pagesError/error";
        }

    }

    /*********************************Event Detail ****************************/

    @GetMapping(value = "/eventDetail")

    public String eventDetail(Model model, Long id) {
        try {

            evenementService.eventDetailService(model, id);


            return "evenement/evenementDetail";
        } catch (Exception e) {
            System.out.println(e);
            return "pagesError/error";
        }
    }

}
