package com.proxym.pfe.association.gestion_cotisations.controllers;

import com.proxym.pfe.association.gestion_cotisations.dto.CotisationDto;
import com.proxym.pfe.association.gestion_cotisations.entities.Cotisation;
import com.proxym.pfe.association.gestion_cotisations.services.CotisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/cotisations/")
public class CotisationController {
    @Autowired
    CotisationService cotisationService;

    /*********************************add cotisation formulaire******************************/
    @GetMapping(value = "formulaire")
    public String formulaireAddCotisation(Model model) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String dateMin = format.format(new Date());

        model.addAttribute("now", dateMin);

        model.addAttribute("cotisationDto", new CotisationDto());

        return "cotisation/add-cotisation";
    }

    /*********************************add cotisation save action******************************/

    @PostMapping(value = "/save")
    public String saveCotisation(@Valid CotisationDto cotisationDto,
                                 BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
                                 @RequestParam(name = "dateDebut", defaultValue = "2020-06-05") String dateDebut
            , @RequestParam(name = "dateFin", defaultValue = "2020-06-06") String dateFin) {
        try {
            Date dateD = new SimpleDateFormat("yyyy-MM-dd").parse(dateDebut);
            Date dateF = new SimpleDateFormat("yyyy-MM-dd").parse(dateFin);
            if (bindingResult.hasErrors() || dateD.compareTo(dateF) > 0) {

                if (dateD.compareTo(dateF) > 0) {


                    model.addAttribute("errorDate", "La date saisie est incorrect, la date fin doit etre ultèrieur à la date début");
                }

                return "cotisation/add-cotisation";
            }

            cotisationService.addCotisationServ(cotisationDto);
            redirectAttributes.addFlashAttribute("messageAdd", " Votre cotisation a été ajouté avec succès ");

            return "redirect:/cotisations/listCotisation";

        } catch (Exception e) {
            System.out.println("errorrrr");
            return "pagesError/error";
        }

    }

    /*********************************list cotisations******************************/

    @RequestMapping(value = "/listCotisation", method = RequestMethod.GET)
    public String index(Model model) {
        List<Cotisation> cotisationList = cotisationService.listCotisationServ();

        model.addAttribute("pagesCotisations", cotisationList);
        return "cotisation/list-cotisation";
    }

    /*********************************delete cotisation******************************/

    @RequestMapping(value = "/supprimer")
    public String supprimer(Long id, RedirectAttributes redirectAttributes) {
        cotisationService.supprimerCotisationServ(id);
        redirectAttributes.addFlashAttribute("messageDelete", " Votre cotisation a été supprimé avec succès ");

        return "redirect:/cotisations/listCotisation";
    }

    /*********************************update cotisation******************************/

    @RequestMapping(value = "/formulaireUpdate", method = RequestMethod.GET)

    public String formulaireUpdate(Model model, Long id) {
        Cotisation cotisation = cotisationService.getOneServ(id);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String dateMin = format.format(new Date());

        model.addAttribute("now", dateMin);
        model.addAttribute("cotisation", cotisation);


        return "cotisation/modifier-cotisation";
    }

    @PostMapping(value = "/modifierCotisation")
    public String modifierSponsor(@Valid Cotisation cotisation, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, @RequestParam(name = "dateDebut", defaultValue = "2020-06-05") String dateDebut
            , @RequestParam(name = "dateFin", defaultValue = "2020-06-06") String dateFin) {
        try {
            Date dateD = new SimpleDateFormat("yyyy-MM-dd").parse(dateDebut);
            Date dateF = new SimpleDateFormat("yyyy-MM-dd").parse(dateFin);


            if (bindingResult.hasErrors() || dateD.compareTo(dateF) > 0) {


                if (dateD.compareTo(dateF) > 0) {


                    model.addAttribute("errorDate", "La date saisie est incorrect, la date fin doit etre ultèrieur à la date début");
                }

                System.out.println("bindingResult.hasErrors()" + bindingResult.hasErrors());

                return "cotisation/modifier-cotisation";
            }


            cotisationService.updateCotisationServ(cotisation);

            redirectAttributes.addFlashAttribute("messageUpdate", " Votre cotisation a été modifié avec succès ");

            return "redirect:/cotisations/listCotisation";

        } catch (Exception e) {
            System.out.println("errorrrr");
            return "pagesError/error";
        }

    }
}
