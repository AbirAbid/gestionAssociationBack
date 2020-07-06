package com.proxym.pfe.association.gestion_sponsors.controllers;

import com.proxym.pfe.association.gestion_sponsors.entities.Sponsor;
import com.proxym.pfe.association.gestion_sponsors.services.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/sponsors/")
public class SponsorController {
    @Autowired
    SponsorService sponsorService;

    /***********************************************Add sponsor*******************************************/

    @GetMapping(value = "formulaire")
    public String formulaireAddSponsor(Model model) {

        model.addAttribute("sponsor", new Sponsor());

        return "sponsor/addSponsor";
    }


    @PostMapping(value = "/save")
    public String saveSponsor(@Valid Sponsor sponsor,
                              BindingResult bindingResult, @RequestParam(name = "picture") MultipartFile file,
                              RedirectAttributes redirectAttributes) {
        try {
            if (bindingResult.hasErrors()) {
                System.out.println("bindingResult.hasErrors()" + bindingResult.hasErrors());
                return "sponsor/addSponsor";
            }
            sponsor.setAffecte(0);
            sponsorService.saveSponsorService(sponsor, file);
            redirectAttributes.addFlashAttribute("messageAdd", " Votre sponsor a été ajouté avec succès ");

            return "redirect:/sponsors/sponsors";

        } catch (Exception e) {
            System.out.println("errorrrr");
            return "pagesError/error";
        }

    }

    /***********************************************Delete sponsor*******************************************/

    @RequestMapping(value = "/supprimer")
    public String supprimer(Long id, RedirectAttributes redirectAttributes) {
        sponsorService.supprimerSponsor(id);
        redirectAttributes.addFlashAttribute("messageDelete", " Votre sponsor a été supprimé avec succès ");

        return "redirect:/sponsors/sponsors";
    }


    /***********************************************List sponsors*******************************************/

    @RequestMapping(value = "/sponsors", method = RequestMethod.GET)
    public String listsponsor(Model model) {
        List<Sponsor>  sponsorList=sponsorService.findAllSponsorServ();

        model.addAttribute("sponsorlist", sponsorList);

        return "sponsor/listSponsor";
    }

    @RequestMapping(value = "/getPhoto", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getPhoto(Long id) throws Exception {
        return sponsorService.getPhotoService(id);
    }

    /*********************************Update Sponsor****************************/
    @RequestMapping(value = "/formulaireUpdate")

    public String formulaireUpdate(Model model, Long id) {
        Sponsor s = sponsorService.getOneService(id);
        System.out.println("sponsor : " + s);
        model.addAttribute("sponsor", s);

        return "sponsor/modifierSponsor";
    }

    @PostMapping(value = "/modifierSponsor")
    public String modifierSponsor(@Valid Sponsor sponsor, BindingResult bindingResult, @RequestParam(name = "picture") MultipartFile file,
                                  RedirectAttributes redirectAttributes
    ) {
        try {
            if (bindingResult.hasErrors()) {
                System.out.println("bindingResult.hasErrors()" + bindingResult.hasErrors());

                return "sponsor/modifierSponsor";
            }
            // pour n'est pas ecraser the same name


            sponsorService.saveSponsorService(sponsor, file);


            redirectAttributes.addFlashAttribute("messageUpdate", " Votre sponsor a été modifié avec succès ");


            return "redirect:/sponsors/sponsors";

        } catch (Exception e) {
            System.out.println("errorrrr");
            return "pagesError/error";
        }

    }
}
