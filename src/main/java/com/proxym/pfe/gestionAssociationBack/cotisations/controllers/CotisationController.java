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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /*********************************add cotisation******************************/
    @GetMapping(value = "formulaire")
    public String formulaireAddCotisation(Model model) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String dateMin = format.format( new Date()   );

        model.addAttribute("now", dateMin);

        model.addAttribute("cotisationDto", new CotisationDto());

        return "cotisation/add-cotisation";
    }

    @PostMapping(value = "/save")
    //  public String saveCotisation(@Valid Cotisation cotisation,
    public String saveCotisation(Model model, @Valid CotisationDto cotisationDto,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        try {
            if (bindingResult.hasErrors()) {
                System.out.println("bindingResult.hasErrors()" + bindingResult.hasErrors());
                System.out.println("bindingResult   " + bindingResult);
                // model.addAttribute("cotisation", cotisation);

                return "cotisation/add-cotisation";
            }
            Cotisation cotisation1 = new Cotisation();
            cotisation1.setCotisationName(cotisationDto.getCotisationName());
            cotisation1.setDateDebut(cotisationDto.getDateDebut());
            cotisation1.setMontant(cotisationDto.getMontant());
            cotisation1.setDateFin(cotisationDto.getDateFin());
            cotisationService.addCotisationServ(cotisation1);
            redirectAttributes.addFlashAttribute("messageAdd", " Votre cotisation a été ajouté avec succès ");

            return "redirect:/cotisations/listCotisation";

        } catch (Exception e) {
            System.out.println("errorrrr");
            return "pagesError/error";
        }

    }

    /*********************************list cotisations******************************/

    @RequestMapping(value = "/listCotisation")
    public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "motCle", defaultValue = "") String mc) {
        //cherche moi un paam page à dispa servlet initialement page0
        //pour connaitre page
        Page<Cotisation> pagesCotisations = cotisationService.rehercherPageCotisationServ("%" + mc + "%", new PageRequest(page, 5));
        System.out.println("pagesSponsors" + pagesCotisations.getContent());

        int pagesCount = pagesCotisations.getTotalPages();
        int[] pages = new int[pagesCount];

        for (int i = 0; i < pagesCount; i++) {
            pages[i] = i;
            System.out.println(" pages[i] " + pages[i]);
        }
         List<Cotisation> cotisationList = cotisationService.listCotisationServ();

        model.addAttribute("pages", pages);
        model.addAttribute("pagesCotisations", cotisationList);
        model.addAttribute("pageCourante", page);
        model.addAttribute("pageContent", pagesCotisations.getContent());
        model.addAttribute("mc", mc);
        //model.addAttribute("motCle", mc);
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

    @RequestMapping(value = "/formulaireUpdate")

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

            cotisationService.addCotisationServ(cotisation);

            System.out.println("update cotisation");
            redirectAttributes.addFlashAttribute("messageUpdate", " Votre cotisation a été modifié avec succès ");

            return "redirect:/cotisations/listCotisation";

        } catch (Exception e) {
            System.out.println("errorrrr");
            return "pagesError/error";
        }

    }
}
