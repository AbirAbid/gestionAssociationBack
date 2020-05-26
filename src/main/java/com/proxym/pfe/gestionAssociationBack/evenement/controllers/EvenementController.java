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
import com.proxym.pfe.gestionAssociationBack.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
            , @RequestParam(name = "dateDebut", defaultValue = "2020-04-22") String dateDebut
            , @RequestParam(name = "dateFin", defaultValue = "2020-04-22") String dateFin,
                               RedirectAttributes redirectAttributes) {
        try {
            /*****************Controle Date ***************************/

            Date dateD = new SimpleDateFormat("yyyy-MM-dd").parse(dateDebut);
            Date dateF = new SimpleDateFormat("yyyy-MM-dd").parse(dateFin);

            if (bindingResult.hasErrors() || dateD.compareTo(dateF) > 0) {
                /*****************Controle Date ***************************/
                if (dateD.compareTo(dateF) > 0) {

                    System.out.println("dateD  apres dateF");

                    model.addAttribute("errorDate", "Verifier vos dates");
                }
                /** End Controle Date **/

                List<Sponsor> sponsors = sponsorService.findAllSponsorServ();
                model.addAttribute("sponsors", sponsors);

                return "evenement/add-event";
            }
            /** Champs event form1 **/

            evenementService.AjouterEvent(evenementDto);


            redirectAttributes.addFlashAttribute("message", " Votre événement a été ajouté avec succès ");

            return "redirect:list";
        } catch (Exception e) {
            System.out.println(e);
            return "pagesError/error";
        }
    }


    /*********************************Update Event****************************/
    @RequestMapping(value = "/formulaireUpdateEvent")

    public String formulaireUpdate(Model model, Long id) {
        try {


            List<Sponsor> sponsors = sponsorService.findAllSponsorServ();
            model.addAttribute("sponsors", sponsors);

            model.addAttribute("evenementDto", evenementService.formulaireUpdate(id));

            return "evenement/updateEvent";
        } catch (Exception e) {
            System.out.println(e);
            return "pagesError/error";
        }
    }

    @PostMapping(value = "/update")
    public String updateEvent(@Valid EvenementDto evenementDto,
                              BindingResult bindingResult, Model model
            , @RequestParam(name = "dateDebut", defaultValue = "2020-04-22") String dateDebut
            , @RequestParam(name = "dateFin", defaultValue = "2020-04-22") String dateFin,
                              RedirectAttributes redirectAttributes) {
        try {
            /*****************Controle Date ***************************/

            Date dateD = new SimpleDateFormat("yyyy-MM-dd").parse(dateDebut);
            Date dateF = new SimpleDateFormat("yyyy-MM-dd").parse(dateFin);

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

            evenementService.AjouterEvent(evenementDto);


            redirectAttributes.addFlashAttribute("messageUpdate", " Votre événement a été modifié avec succès ");

            return "redirect:list";
        } catch (Exception e) {
            System.out.println(e);
            return "pagesError/error";
        }
    }


    /*********************************Delete Event******************************/
    @RequestMapping(value = "/supprimer")
    public String supprimer(Long id, RedirectAttributes redirectAttributes) {
        evenementService.suuprimerEvent(id);
        redirectAttributes.addFlashAttribute("messageDelete", " Votre événement a été supprimé avec succès .");

        return "redirect:/evenement/list";
    }


    /*********************************List Page  Event****************************/

    @GetMapping(value = "/list")
    public String showList(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "motCle", defaultValue = "") String mc) {
        try {
            Page<Evenement> evenements = evenementService.rehercherPageEvenementService("%" + mc + "%", new PageRequest(page, 5));

            int pagesCount = evenements.getTotalPages();
            int[] pages = new int[pagesCount];

            for (int i = 0; i < pagesCount; i++) {
                pages[i] = i;
                System.out.println(" pages[i] " + pages[i]);
            }

            model.addAttribute("pageCourante", page);
            model.addAttribute("pageContent", evenements.getContent());
            model.addAttribute("mc", mc);
            model.addAttribute("pages", pages);
            model.addAttribute("dateJour", new Date());
            model.addAttribute("evenements", evenements);
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

            Evenement e = evenementService.getOneEventByIdservice(id);

            List<Bien> biens = bienService.findAllByEventService(id);
            System.out.println("************************okok*********************");

            List<Mission> missions = missionService.findAllMissionByEventService(id);

            /*************** pour avoir liste donneurs************************/
            List<User> users = userService.getAllDonneursService();
            List<UserBien> userBiens = new ArrayList<>();
            for (int i = 0; i < users.size(); i++) {
                for (int j = 0; j < users.get(i).getUserBiens().size(); j++) {
                    userBiens.add(users.get(i).getUserBiens().get(j));
                }
            }
            List<UserBien> userBiensEvent = new ArrayList<>();
            for (int i = 0; i < userBiens.size(); i++) {
                if (userBiens.get(i).getBien().getEvenement() == e) {
                    userBiensEvent.add(userBiens.get(i));
                }
            }
            /** pour éliminer redondance ***/
            Set<UserBien> mySet = new HashSet<>(userBiensEvent);
            userBiensEvent = new ArrayList<>(mySet);

            /*************** pour avoir liste missions*************************/

            List<User> usersBenevole = userService.getAllBenevolesService();
            List<UserMission> userMissions = new ArrayList<>();

            for (int i = 0; i < usersBenevole.size(); i++) {
                for (int j = 0; j < usersBenevole.get(i).getUserMissions().size(); j++) {

                    userMissions.add(usersBenevole.get(i).getUserMissions().get(j));

                }

            }

            List<UserMission> userMissionsEvent = new ArrayList<>();
            for (int i = 0; i < userMissions.size(); i++) {
                if (userMissions.get(i).getMission().getEvenement() == e) {
                    userMissionsEvent.add(userMissions.get(i));
                }
            }
            /** pour éliminer redondance ***/
            Set<UserMission> mySet1 = new HashSet<>(userMissionsEvent);
            userMissionsEvent = new ArrayList<>(mySet1);


            model.addAttribute("evenement", e);
            model.addAttribute("biens", biens);
            model.addAttribute("userBiens", userBiensEvent);
            model.addAttribute("missions", missions);
            model.addAttribute("userMission", userMissionsEvent);


            return "evenement/evenementDetail";
        } catch (Exception e) {
            System.out.println(e);
            return "pagesError/error";
        }
    }
}

