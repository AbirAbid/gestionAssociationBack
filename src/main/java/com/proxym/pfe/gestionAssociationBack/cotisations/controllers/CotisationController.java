package com.proxym.pfe.gestionAssociationBack.cotisations.controllers;

import com.proxym.pfe.gestionAssociationBack.cotisations.dto.CotisationDto;
import com.proxym.pfe.gestionAssociationBack.cotisations.entities.Cotisation;
import com.proxym.pfe.gestionAssociationBack.cotisations.services.CotisationService;
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

        String dateMin = format.format( new Date()   );

        model.addAttribute("now", dateMin);

        model.addAttribute("cotisationDto", new CotisationDto());

        return "cotisation/add-cotisation";
    }
    /*********************************add cotisation save action******************************/

    @PostMapping(value = "/save")
    public String saveCotisation( @Valid CotisationDto cotisationDto,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        try {
            if (bindingResult.hasErrors()) {
                System.out.println("bindingResult.hasErrors()" + bindingResult.hasErrors());
                System.out.println("bindingResult   " + bindingResult);

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

    @RequestMapping(value = "/listCotisation",method = RequestMethod.GET)
    public String index(Model model) {
         List<Cotisation> cotisationList = cotisationService.listCotisationServ();

        model.addAttribute("pagesCotisations", cotisationList);
        return "cotisation/list-cotisation";
    }

    /*********************************delete cotisation******************************/

    @RequestMapping(value = "/supprimer")
    public String supprimer(Long id,RedirectAttributes redirectAttributes) {
        cotisationService.supprimerCotisationServ(id);
        redirectAttributes.addFlashAttribute("messageDelete", " Votre cotisation a été supprimé avec succès ");

        return "redirect:/cotisations/listCotisation";
    }

    /*********************************update cotisation******************************/

    @RequestMapping(value = "/formulaireUpdate",method = RequestMethod.GET)

    public String formulaireUpdate(Model model, Long id) {
        Cotisation cotisation = cotisationService.getOneServ(id);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String dateMin = format.format( new Date()   );

        model.addAttribute("now", dateMin);
        model.addAttribute("cotisation", cotisation);


        return "cotisation/modifier-cotisation";
    }

    @PostMapping(value = "/modifierCotisation")
    public String modifierSponsor(@Valid Cotisation cotisation, BindingResult bindingResult,RedirectAttributes redirectAttributes) {
        try {
            if (bindingResult.hasErrors()) {
                System.out.println("bindingResult.hasErrors()" + bindingResult.hasErrors());

                return "cotisation/modifier-cotisation";
            }
            // pou n'est pas ecraser le meme nom

            cotisationService.updateCotisationServ(cotisation);

            redirectAttributes.addFlashAttribute("messageUpdate", " Votre cotisation a été modifié avec succès ");

            return "redirect:/cotisations/listCotisation";

        } catch (Exception e) {
            System.out.println("errorrrr");
            return "pagesError/error";
        }

    }
}
