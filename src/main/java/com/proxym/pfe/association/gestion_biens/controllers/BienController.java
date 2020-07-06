package com.proxym.pfe.association.gestion_biens.controllers;

import com.proxym.pfe.association.gestion_evenements.services.EvenementService;
import com.proxym.pfe.association.gestion_utilisateurs.dto.MailToSend;
import com.proxym.pfe.association.gestion_biens.models.entities.UserBien;
import com.proxym.pfe.association.gestion_biens.models.services.BienService;
import com.proxym.pfe.association.gestion_utilisateurs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    BienService bienService;
    @Autowired
    EvenementService evenementService;

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

            userService.sendMailMembre(mailToSend);
            redirectAttributes.addFlashAttribute("sendMailMessage", " Votre message a été envoyé avec succès ");

            return "redirect:/bien/listDonneurs";
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
