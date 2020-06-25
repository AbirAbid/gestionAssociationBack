package com.proxym.pfe.gestionAssociationBack.missionBenevole.controllers;

import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.biens.entities.UserBien;
import com.proxym.pfe.gestionAssociationBack.biens.services.BienService;
import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.evenement.services.EvenementService;
import com.proxym.pfe.gestionAssociationBack.membre.entities.MailToSend;
import com.proxym.pfe.gestionAssociationBack.membre.services.MembreService;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.dto.UserMissionDto;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.Mission;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.UserMission;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.services.MissionService;
import com.proxym.pfe.gestionAssociationBack.sponsors.services.SponsorService;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import com.proxym.pfe.gestionAssociationBack.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/mission/")
public class MissionController {
    @Autowired
    UserService userService;
    @Autowired
    MissionService missionService;
    @Autowired
    MembreService membreService;
    @Autowired
    EvenementService evenementService;


    @RequestMapping(value = "/listbenevoles", method = RequestMethod.GET)
    public String listBenevoles(Model model) {

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

            String dateJourString = format.format(new Date());
            Date dateJour = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(dateJourString);


            model.addAttribute("dateJour", dateJour);
            List<UserMission> userMissions = missionService.getListMissionUser();
            List<UserMissionDto> userMissionDtos = missionService.getListMissionUserDto();
            model.addAttribute("dateDispon", userMissionDtos);

            model.addAttribute("userMissions", userMissions);


            return "benevoles/liste-benevoles";
        } catch (Exception e) {
            return "pagesError/error";
        }


    }


    @RequestMapping(value = "/affectMissionUrl", method = RequestMethod.GET)

    public String affectMission(String username, Long id,
                                RedirectAttributes redirectAttributes) {
        try {

            missionService.affecterMission(username, id);
            User user = userService.findUserByUsernameService(username);
            Mission mission = missionService.findMissionByIdService(id);
            redirectAttributes.addFlashAttribute("message", "Vous avez affecter " + user.getNom() + " à " + mission.getTitre());

            return "redirect:/mission/listbenevoles";
        } catch (Exception e) {
            return "pagesError/error";
        }
    }

    /******************************Liberer Missio *********************************************/
    @RequestMapping(value = "/libererMissionUrl", method = RequestMethod.GET)

    public String libererMission(String username, Long id,
                                 RedirectAttributes redirectAttributes) {
        try {

            User user = userService.findUserByUsernameService(username);
            missionService.libererMission(username, id);

            redirectAttributes.addFlashAttribute("messageFree", "Vous avez libérer " + user.getNom());

            return "redirect:/mission/listbenevoles";
        } catch (Exception e) {
            return "pagesError/error";
        }
    }


    /***send email*****/
    @RequestMapping(value = "/sendmailUrL")
    public String sendMail(Model model, String id) {

        try {
            MailToSend mailToSend = new MailToSend();
            mailToSend.setReceiver(id);
            model.addAttribute("mailSend", mailToSend);

            return "benevoles/benevolesSendMail";
        } catch (Exception e) {
            return "pagesError/error";
        }


    }

    @RequestMapping(value = "/sendHtmlEmailUrl")
    public String sendHtmlEmail(MailToSend mailToSend, RedirectAttributes redirectAttributes) {
        try {

            membreService.sendMailMembre(mailToSend);
            redirectAttributes.addFlashAttribute("sendMailMessage", " Votre message a été envoyé avec succès ");

            return "redirect:/mission/listbenevoles";
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

            return "redirect:/evenement/eventDetail?id="+id;

        } catch (Exception e) {
            System.out.println(e);
            return "pagesError/error";
        }
    }

}
