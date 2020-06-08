package com.proxym.pfe.gestionAssociationBack.biens.controllers;

import com.proxym.pfe.gestionAssociationBack.biens.entities.UserBien;
import com.proxym.pfe.gestionAssociationBack.biens.services.BienService;
import com.proxym.pfe.gestionAssociationBack.membre.entities.MailToSend;
import com.proxym.pfe.gestionAssociationBack.membre.services.MembreService;
import com.proxym.pfe.gestionAssociationBack.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/bien/")

public class BienController {
    @Autowired
    UserService userService;

    @Autowired
    MembreService membreService;
    @Autowired
    BienService bienService;

    @RequestMapping(value = "/listDonneurs",method = RequestMethod.GET)
    public String listDonneurs(Model model) {

        try {
            List<UserBien> userBiens = bienService.getListUserBien();
            model.addAttribute("userBiens", userBiens);
            return "donneurs/list_donneurs";
        } catch (Exception e) {
            return "pagesError/error";
        }

    }


    @RequestMapping(value = "/sendmailUrL",method = RequestMethod.GET)
    public String sendMail(Model model, String id) {

        try {
            MailToSend mailToSend = new MailToSend();
            mailToSend.setReceiver(id);
            model.addAttribute("mailSend", mailToSend);

            return "donneurs/donneursSendMail";
        } catch (Exception e) {
            return "pagesError/error";
        }


    }

    @RequestMapping(value = "/sendHtmlEmailUrl",method = RequestMethod.GET)
    public String sendHtmlEmail(MailToSend mailToSend, RedirectAttributes redirectAttributes) {
        try {

            membreService.sendMailMembre(mailToSend);
            redirectAttributes.addFlashAttribute("sendMailMessage", " Votre message a été envoyé avec succès ");

            return "redirect:/bien/listDonneurs";
        } catch (Exception e) {
            System.out.println("Exception  " + e);
            return "pagesError/error";
        }

    }


}
