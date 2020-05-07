package com.proxym.pfe.gestionAssociationBack.evenement.controllers;

import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.biens.entities.ParticiperBien;
import com.proxym.pfe.gestionAssociationBack.biens.services.BienService;
import com.proxym.pfe.gestionAssociationBack.biens.services.ParticiperBienService;
import com.proxym.pfe.gestionAssociationBack.evenement.dto.EvenementDto;
import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.evenement.services.EvenementService;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.Mission;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.MissionBenevole;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.services.MissionBenevoleService;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.services.MissionService;
import com.proxym.pfe.gestionAssociationBack.sponsors.entities.Sponsor;
import com.proxym.pfe.gestionAssociationBack.sponsors.repositories.SponsorRepository;
import com.proxym.pfe.gestionAssociationBack.sponsors.services.SponsorService;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    MissionBenevoleService missionBenevoleService;
    @Autowired
    MissionService missionService;
    @Autowired
    SponsorService sponsorService;
    @Autowired
    SponsorRepository sponsorRepository;
    @Autowired
    ParticiperBienService participerBienService;

    /*********************************add Event****************************/


    @GetMapping(value = "formulaire")
    public String formulaireAddEvent(Model model) {
        try {
            List<Sponsor> sponsors = sponsorService.findAllSponsorServ();
            EvenementDto evenementDto = new EvenementDto();
            evenementDto.addBien(new Bien());
           // evenementDto.addMissionBenevole(new MissionBenevole());
            evenementDto.addMission(new Mission());
            //   for (int i = 1; i <= 3; i++) {
            // evenementDto.addBien(new Bien());
            //evenementDto.addMissionBenevole(new MissionBenevole());
            //  }
            model.addAttribute("evenementDto", evenementDto);
            System.out.println("sponsors" + sponsors);
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
            , @RequestParam(name = "dateFin", defaultValue = "2020-04-22") String dateFin) {
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
                return "evenement/add-event";
            }
            /** Champs event form1 **/


            Evenement event = new Evenement();
            event.setTitre(evenementDto.getTitre());
            event.setDescription(evenementDto.getDescription());
            event.setAdresse(evenementDto.getAdresse());
            event.setDateDebut(evenementDto.getDateDebut());
            event.setDateFin(evenementDto.getDateFin());
            event.setSponsors(evenementDto.getSponsors());
            event.setVille(evenementDto.getVille());
            event.setFrais(evenementDto.getFrais());
            event.setActive(0);
            System.out.println("***********************event Sponsor*********************  " + event.getSponsors());

            Evenement e = evenementService.addEventService(event);
            /** for Affect  Sponsor ***/
            List<Sponsor> sponsors = event.getSponsors();

            for (int i = 0; i <= sponsors.size() - 1; i++) {
                Sponsor s = sponsors.get(i);
                s.setAffecte(1);
                sponsorService.updateSponsorService(s);

            }
            /** End Champs event form1 **/

            //  System.out.println("evenementDto.getBiens():****** " + evenementDto.getBiens());

            for (int i = 0; i <= evenementDto.getBiens().size() - 1; i++) {

                evenementDto.getBiens().get(i).setEvenement(e);
                evenementDto.getBiens().get(i).setTotaleqteDonnee(0);
            }

            // System.out.println("evenementDto.getMissionBenevoles():****** " + evenementDto.getMissionBenevoles());

            for (int i = 0; i <= evenementDto.getMissions().size() - 1; i++) {


                evenementDto.getMissions().get(i).setEvenement(e);

            }

            bienService.saveAllService(evenementDto.getBiens());
            missionService.saveAllMissionService(evenementDto.getMissions());


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

            Evenement e = evenementService.getOneEventByIdservice(id);

            EvenementDto evenementDto = new EvenementDto();
            /*if (e.getSponsors().isEmpty()) {
                List<Sponsor> sponsors = sponsorService.findAllSponsorServ();
             //   model.addAttribute("sponsors", sponsors);
            }else {evenementDto.setSponsors(e.getSponsors());}*/
            List<Sponsor> sponsors = sponsorService.findAllSponsorServ();
            model.addAttribute("sponsors", sponsors);

            System.out.println(" e.getSponsors()    " + e.getSponsors());

            evenementDto.affectToEventDto(e);
            System.out.println(" evenementDto().getSponsors()    " + evenementDto.getSponsors());

            List<Bien> biens = bienService.findAllByEventService(id);
           // List<MissionBenevole> missionBenevoles = missionBenevoleService.findAllMissionByEventService(id);
            List<Mission> missions=missionService.findAllMissionByEventService(id);
            System.out.println("******biens.isEmpty()*****" + biens.isEmpty());
            if (biens.size() != 0) {
                for (int i = 0; i <= biens.size() - 1; i++) {
                    evenementDto.addBien(biens.get(i));
                }
            } else {

                evenementDto.addBien(new Bien());

            }
            if (missions.size() != 0) {
                for (int i = 0; i <= missions.size() - 1; i++) {
                    evenementDto.addMission(missions.get(i));
                    System.out.println("missionBenevoles.get(i)*******" + missions.get(i).getId());


                }
            } else {

                evenementDto.addMission(new Mission());

            }


            model.addAttribute("evenementDto", evenementDto);

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
            , @RequestParam(name = "dateFin", defaultValue = "2020-04-22") String dateFin) {
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

            Evenement event = new Evenement();
            event.setId(evenementDto.getId());
            event.setTitre(evenementDto.getTitre());
            event.setDescription(evenementDto.getDescription());
            event.setAdresse(evenementDto.getAdresse());
            event.setDateDebut(evenementDto.getDateDebut());
            event.setDateFin(evenementDto.getDateFin());
            event.setSponsors(evenementDto.getSponsors());
            event.setVille(evenementDto.getVille());
            event.setFrais(evenementDto.getFrais());
            // System.out.println("***********************event Sponsor*********************  " + event.getSponsors());
            Evenement e = evenementService.addEventService(event);
            /** for Affect  Sponsor ***/
            List<Sponsor> sponsors = (List) event.getSponsors();

            for (int i = 0; i <= sponsors.size() - 1; i++) {
                Sponsor s = sponsors.get(i);
                s.setAffecte(1);
                sponsorService.updateSponsorService(s);

            }
            List<Bien> biens = bienService.findAllByEventService(e.getId());

            for (int i = 0; i <= evenementDto.getBiens().size() - 1; i++) {
                if (i <= biens.size() - 1) {
                    evenementDto.getBiens().get(i).setId(biens.get(i).getId());
                }
                evenementDto.getBiens().get(i).setEvenement(e);

            }
            List<Mission> missions = missionService.findAllMissionByEventService(e.getId());
            System.out.println("evenementDto.getMissionBenevoles().size() ************" + evenementDto.getMissions().size());

            for (int i = 0; i <= evenementDto.getMissions().size() - 1; i++) {


                if (i <= missions.size() - 1) {
                    evenementDto.getMissions().get(i).setId(missions.get(i).getId());
                }

                System.out.println("mission id ************" + evenementDto.getMissions().get(i));
                evenementDto.getMissions().get(i).setEvenement(e);


            }
            bienService.saveAllService(evenementDto.getBiens());
            missionService.saveAllMissionService(evenementDto.getMissions());


            return "redirect:list";
        } catch (Exception e) {
            System.out.println(e);
            return "pagesError/error";
        }
    }


    /*********************************Delete Event******************************/
    @RequestMapping(value = "/supprimer")
    public String supprimer(Long id) {
        evenementService.suuprimerEvent(id);
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
            model.addAttribute("evenements", evenements);

            return "evenement/list-event";
        } catch (Exception e) {
            System.out.println(e);
            return "pagesError/error";
        }


    }

}

