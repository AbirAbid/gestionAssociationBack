package com.proxym.pfe.gestionAssociationBack.sponsors.controllers;

import com.proxym.pfe.gestionAssociationBack.sponsors.entities.Sponsor;
import com.proxym.pfe.gestionAssociationBack.sponsors.services.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequestMapping("/sponsors/")
public class SponsorController {
    @Autowired
    SponsorService sponsorService;


    @GetMapping(value = "formulaire")
    public String formulaireAddSponsor(Model model) {

        model.addAttribute("sponsor", new Sponsor());

        return "sponsor/addSponsor";
    }


    @RequestMapping(value = "/supprimer")
    public String supprimer(Long id) {
        sponsorService.supprimerSponsor(id);
        return "redirect:/sponsors/sponsors";
    }


    @PostMapping(value = "/save")
    public String saveSponsor(@Valid Sponsor sponsor,
                              BindingResult bindingResult, @RequestParam(name = "picture") MultipartFile file) {
        try {
            if (bindingResult.hasErrors()) {
                System.out.println("bindingResult.hasErrors()" + bindingResult.hasErrors());
                return "sponsor/addSponsor";
            }
            sponsor.setAffecte(0);
            sponsorService.saveSponsorService(sponsor, file);
            return "redirect:/sponsors/sponsors";

        } catch (Exception e) {
            System.out.println("errorrrr");
            return "pagesError/error";
        }

    }

    @RequestMapping(value = "/sponsors")
    public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "motCle", defaultValue = "") String mc) {
        //cherche moi un paam page à dispa servlet initialement page0
        // Page<Etudiant> pagesetudiants = etudiantRepository.findAll(new PageRequest(page, 5));
        //pour connaitre page
        Page<Sponsor> pagesSponsors = sponsorService.rehercherPageSponsorSrv("%" + mc + "%", new PageRequest(page, 5));
        System.out.println("pagesSponsors" + pagesSponsors.getContent());
        //  Page<Sponsor> pagesSponsors = sponsorService.findAllSponsorServ(new PageRequest(page, 5));

        int pagesCount = pagesSponsors.getTotalPages();
        int[] pages = new int[pagesCount];

        for (int i = 0; i < pagesCount; i++) {
            pages[i] = i;
            System.out.println(" pages[i] " + pages[i]);
        }
        model.addAttribute("pages", pages);
        model.addAttribute("pagesSponsors", pagesSponsors);
        model.addAttribute("pageCourante", page);
        model.addAttribute("pageContent", pagesSponsors.getContent());
        model.addAttribute("mc", mc);
        //model.addAttribute("motCle", mc);
        return "sponsor/listSponsor";
    }

    @RequestMapping(value = "/getPhoto", produces = MediaType.IMAGE_JPEG_VALUE)
    //@ResponseBody resultat va etre envoyer corps de la reponse
    @ResponseBody
    public byte[] getPhoto(Long id) throws Exception {
        return sponsorService.getPhotoService(id);
    }

    /*********************************Update Sponsor****************************/
    @RequestMapping(value = "/formulaireUpdate")

    public String formulaireUpdate(Model model, Long id) {
        Sponsor s = sponsorService.getOneService(id);

        model.addAttribute("sponsor", s);

        return "sponsor/modifierSponsor";
    }

    @PostMapping(value = "/modifierSponsor")
    public String modifierSponsor(@Valid Sponsor sponsor, BindingResult bindingResult, @RequestParam(name = "picture") MultipartFile file
    ) {
        try {
            if (bindingResult.hasErrors()) {
                System.out.println("bindingResult.hasErrors()" + bindingResult.hasErrors());

                return "sponsor/modifierSponsor";
            }
            // pou n'est pas ecraser le meme nom


            sponsorService.saveSponsorService(sponsor, file);


            System.out.println("update sponsor");
            //  etudiantRepository.save(et);
            return "redirect:/sponsors/sponsors";

        } catch (Exception e) {
            System.out.println("errorrrr");
            return "pagesError/error";
        }

    }
}
