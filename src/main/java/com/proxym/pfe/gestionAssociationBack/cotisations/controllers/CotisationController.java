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

import javax.validation.Valid;

@Controller
@RequestMapping("/cotisations/")
public class CotisationController {
    @Autowired
    CotisationService cotisationService;

    @GetMapping(value = "formulaire")
    public String formulaireAddCotisation(Model model) {

        model.addAttribute("cotisationDto", new CotisationDto());

        return "cotisation/add-cotisation";
    }

    @PostMapping(value = "/save")
    //  public String saveCotisation(@Valid Cotisation cotisation,
    public String saveCotisation(Model model, @Valid CotisationDto cotisationDto,
                                 BindingResult bindingResult) {
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
            return "redirect:/cotisations/listCotisation";

        } catch (Exception e) {
            System.out.println("errorrrr");
            return "pagesError/error";
        }

    }

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
        model.addAttribute("pages", pages);
        model.addAttribute("pagesCotisations", pagesCotisations);
        model.addAttribute("pageCourante", page);
        model.addAttribute("pageContent", pagesCotisations.getContent());
        model.addAttribute("mc", mc);
        //model.addAttribute("motCle", mc);
        return "cotisation/list-cotisation";
    }

    @RequestMapping(value = "/supprimer")
    public String supprimer(Long id) {
        cotisationService.supprimerCotisationServ(id);
        return "redirect:/cotisations/listCotisation";
    }

    @RequestMapping(value = "/formulaireUpdate")

    public String formulaireUpdate(Model model, Long id) {
        Cotisation cotisation = cotisationService.getOneServ(id);
        model.addAttribute("cotisation", cotisation);


        return "cotisation/modifier-cotisation";
    }

    @PostMapping(value = "/modifierCotisation")
    public String modifierSponsor(@Valid Cotisation cotisation, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                System.out.println("bindingResult.hasErrors()" + bindingResult.hasErrors());

                return "cotisation/modifier-cotisation";
            }
            // pou n'est pas ecraser le meme nom

            cotisationService.addCotisationServ(cotisation);

            System.out.println("update cotisation");
            //  etudiantRepository.save(et);
            return "redirect:/cotisations/listCotisation";

        } catch (Exception e) {
            System.out.println("errorrrr");
            return "pagesError/error";
        }

    }
}
