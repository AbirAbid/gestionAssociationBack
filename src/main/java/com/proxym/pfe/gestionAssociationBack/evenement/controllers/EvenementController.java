package com.proxym.pfe.gestionAssociationBack.evenement.controllers;

import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.biens.entities.UserBien;
import com.proxym.pfe.gestionAssociationBack.biens.services.BienService;
import com.proxym.pfe.gestionAssociationBack.evenement.dto.EvenementDto;
import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.evenement.services.EvenementService;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.Mission;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.UserMission;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.services.MissionService;
import com.proxym.pfe.gestionAssociationBack.sponsors.entities.Sponsor;
import com.proxym.pfe.gestionAssociationBack.sponsors.repositories.SponsorRepository;
import com.proxym.pfe.gestionAssociationBack.sponsors.services.SponsorService;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import com.proxym.pfe.gestionAssociationBack.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/evenement/")
public class EvenementController {

    @Autowired
    EvenementService evenementService;
    @Autowired
    BienService bienService;
    @Autowired
    UserService userService;
    @Autowired
    MissionService missionService;
    @Autowired
    SponsorService sponsorService;
    @Autowired
    SponsorRepository sponsorRepository;


    /*********************************add Event****************************/


    @GetMapping(value = "formulaire")
    public String formulaireAddEvent(Model model) {
        try {
            List<Sponsor> sponsors = sponsorService.findAllSponsorServ();
            EvenementDto evenementDto = new EvenementDto();
            evenementDto.addBien(new Bien());
            evenementDto.addMission(new Mission());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

            String dateMin = format.format(new Date());

            model.addAttribute("now", dateMin);

            model.addAttribute("evenementDto", evenementDto);
            model.addAttribute("sponsors", sponsors);
            return "evenement/add-event";
        } catch (Exception e) {
            System.out.println(e);
            return "pagesError/error";
        }


    }

    @PostMapping(value = "/save")
    public String AjouterEvent(@Valid EvenementDto evenementDto,
                               BindingResult bindingResult, Model model
            , @RequestParam(name = "dateDebut", defaultValue = "2020-06-05T23:45") String dateDebut
            , @RequestParam(name = "dateFin", defaultValue = "2020-06-06T12:12") String dateFin,
                               RedirectAttributes redirectAttributes) {
        try {


            Date dateD = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(dateDebut);
            Date dateF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(dateFin);


            if (bindingResult.hasErrors() || dateD.compareTo(dateF) > 0) {
                if (dateD.compareTo(dateF) > 0) {


                    model.addAttribute("errorDate", "Verifier vos dates");
                }

                List<Sponsor> sponsors = sponsorService.findAllSponsorServ();
                model.addAttribute("sponsors", sponsors);

                return "evenement/add-event";
            }
            /** Champs event form1 **/
            List<Sponsor> sponsors = sponsorService.findAllSponsorServ();
            model.addAttribute("sponsors", sponsors);
            System.out.println("evenementDto::::" + evenementDto);
            evenementService.AjouterEvent(evenementDto);

            evenementService.TauxEchangeForAllUser();
            redirectAttributes.addFlashAttribute("message", " Votre événement a été ajouté avec succès ");

            return "redirect:list";
        } catch (Exception e) {
            System.out.println(e);
            return "pagesError/error";
        }
    }


    /*********************************Update Event****************************/
    @RequestMapping(value = "/formulaireUpdateEvent", method = RequestMethod.GET)

    public String formulaireUpdate(Model model, Long id) {
        try {


            List<Sponsor> sponsors = sponsorService.findAllSponsorServ();
            model.addAttribute("sponsors", sponsors);

            model.addAttribute("evenementDto", evenementService.formulaireUpdate(id));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

            String dateMin = format.format(new Date());

            model.addAttribute("now", dateMin);

            return "evenement/updateEvent";
        } catch (Exception e) {
            System.out.println(e);
            return "pagesError/error";
        }
    }

    @PostMapping(value = "/update")
    public String updateEvent(@Valid EvenementDto evenementDto,
                              BindingResult bindingResult, Model model
            , @RequestParam(name = "dateDebut", defaultValue = "2020-06-05T23:45") String dateDebut
            , @RequestParam(name = "dateFin", defaultValue = "2020-06-07T23:45") String dateFin,
                              RedirectAttributes redirectAttributes) {
        try {
            /*****************Controle Date ***************************/

            Date dateD = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(dateDebut);
            Date dateF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(dateFin);

            if (bindingResult.hasErrors() || dateD.compareTo(dateF) > 0) {
                /*****************Controle Date ***************************/
                if (dateD.compareTo(dateF) > 0) {

                    System.out.println("dateD  après dateF");

                    model.addAttribute("errorDate", "Verifier vos dates");
                }
                /** End Controle Date **/

                List<Sponsor> sponsors = sponsorService.findAllSponsorServ();
                model.addAttribute("sponsors", sponsors);

                System.out.println("bindingResult.hasErrors()******************" + bindingResult.hasErrors());
                System.out.println("bindingResult   " + bindingResult);
                return "evenement/updateEvent";
            }


            evenementService.ModifierEvent(evenementDto);

            redirectAttributes.addFlashAttribute("messageUpdate", " Votre événement a été modifié avec succès ");

            return "redirect:list";
        } catch (Exception e) {
            System.out.println(e);
            return "pagesError/error";
        }
    }


    /*********************************Delete Event******************************/
    @RequestMapping(value = "/supprimer")
    public String supprimer(RedirectAttributes redirectAttributes, Long id) {
        System.out.println("id event controller:::" + id);
        evenementService.supprimerEvent(id);
        redirectAttributes.addFlashAttribute("messageDelete", " Votre événement a été supprimé avec succès .");

        return "redirect:/evenement/list";
    }


    /*********************************List   Event****************************/

    @GetMapping(value = "/list")
    public String showList(Model model) {
        try {
            List<Evenement> evenementsList = evenementService.listEventService();

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

            String dateJourString = format.format(new Date());
            Date dateJour = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(dateJourString);


            model.addAttribute("dateJour", dateJour);
            model.addAttribute("evenements", evenementsList);
            return "evenement/list-event";
        } catch (Exception e) {
            System.out.println(e);
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


    /***********Affecter mission **********/
    @RequestMapping(value = "/affectMissionUrlE", method = RequestMethod.GET)
    public String affectMission(String username, Long id,
                                RedirectAttributes redirectAttributes) {
        try {
            missionService.affecterMission(username, id);
            User user = userService.findUserByUsernameService(username);
            Mission mission = missionService.findMissionByIdService(id);
            Long idEvent = mission.getEvenement().getId();
            redirectAttributes.addFlashAttribute("message", "Vous avez affecter " + user.getNom() + " à " + mission.getTitre());
            return "redirect:/evenement/eventDetail?id=" + idEvent;
        } catch (Exception e) {
            return "pagesError/error";
        }
    }

    /******************************Liberer Mission *********************************************/
    @RequestMapping(value = "/libererMissionUrlE", method = RequestMethod.GET)

    public String libererMission(String username, Long id,
                                 RedirectAttributes redirectAttributes) {
        try {

            User user = userService.findUserByUsernameService(username);
            missionService.libererMission(username, id);
            Mission mission = missionService.findMissionByIdService(id);

            redirectAttributes.addFlashAttribute("messageFree", "Vous avez libérer " + user.getNom());
            Long idEvent = mission.getEvenement().getId();

            return "redirect:/evenement/eventDetail?id=" + idEvent;

        } catch (Exception e) {
            return "pagesError/error";
        }
    }


}

