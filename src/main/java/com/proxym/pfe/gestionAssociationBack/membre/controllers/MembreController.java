package com.proxym.pfe.gestionAssociationBack.membre.controllers;

import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.biens.entities.UserBien;
import com.proxym.pfe.gestionAssociationBack.membre.contenu.MyConstants;
import com.proxym.pfe.gestionAssociationBack.membre.entities.MailToSend;
import com.proxym.pfe.gestionAssociationBack.membre.services.MembreService;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.UserMission;
import com.proxym.pfe.gestionAssociationBack.sponsors.entities.Sponsor;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/membres/")
public class MembreController {
    @Autowired
    MembreService membreService;
    @Autowired
    public JavaMailSender emailSender;

    @GetMapping(value = "listeMembres")
    public String showList(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "motCle", defaultValue = "") String mc) {
        try {
            Page<User> pagesMembresRech = membreService.rehercherPageMembreService("%" + mc + "%", new PageRequest(page, 5));
            //Page<User> pagesMembres = membreService.findAllMembreService(new PageRequest(page, 5));
            //pour compter nombre des pages************
            int pagesCount = pagesMembresRech.getTotalPages();
            int[] pages = new int[pagesCount];

            for (int i = 0; i < pagesCount; i++) {
                pages[i] = i;
                System.out.println(" pages[i] " + pages[i]);
            }

            //**********************
            model.addAttribute("pageCourante", page);
            model.addAttribute("pageContent", pagesMembresRech.getContent());
            model.addAttribute("mc", mc);
            model.addAttribute("pages", pages);
            model.addAttribute("pagesMembres", pagesMembresRech);

            return "membres/membresListe";
        } catch (Exception e) {
            System.out.println(e);
            return "pagesError/error";
        }


    }

    @RequestMapping(value = "/membreDetailUrl")
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

    @RequestMapping(value = "/sendmailUrL")
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


    @ResponseBody
    @RequestMapping("/sendHtmlEmailUrl")
    public String sendHtmlEmail(MailToSend mailToSend) throws MessagingException {
        System.out.println("mailToSend  " + mailToSend);
        MimeMessage message = emailSender.createMimeMessage();

        boolean multipart = true;

        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

        String htmlMsg = mailToSend.getTextToSend();

        message.setContent(htmlMsg, "text/html");
        message.setSubject(mailToSend.getObject());

        helper.setTo(mailToSend.getReceiver());


        this.emailSender.send(message);

        return "Email Sent!";
    }


    @ResponseBody
    @RequestMapping("/sendSimpleEmail")
    public String sendSimpleEmail() {

        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(MyConstants.FRIEND_EMAIL);
        message.setSubject("Test Simple Email");
        message.setText("Hello, Im testing Simple Email");

        // Send Message!
        this.emailSender.send(message);

        return "Email Sent!";
    }

}
