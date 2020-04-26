package com.proxym.pfe.gestionAssociationBack.bookPackage.Etudiant;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;

@Controller
public class EtudiantControlleur {
    @Autowired
    EtudiantRepository etudiantRepository;
    //@value pour injecter une propriete app.propr
    // dir.images propriete dans app.propr
    @Value("${dir.images}")
    private String imageDir;

    @RequestMapping(value = "/etudiant")
    public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "motCle", defaultValue = "") String mc) {
        //cherche moi un paam page à dispa servlet initialement page0
        // Page<Etudiant> pagesetudiants = etudiantRepository.findAll(new PageRequest(page, 5));
        //pour connaitre page
        Page<Etudiant> pagesetudiants = etudiantRepository.chercherEtudiant("%" + mc + "%", new PageRequest(page, 5));

        int pagesCount = pagesetudiants.getTotalPages();
        int[] pages = new int[pagesCount];

        for (int i = 0; i < pagesCount; i++) {
            pages[i] = i;
            System.out.println(" pages[i] " + pages[i]);
        }
        model.addAttribute("pages", pages);
        model.addAttribute("pageEtudiants", pagesetudiants);

        model.addAttribute("pageCourante", page);
        model.addAttribute("motCle", mc);
        return "etudiants";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String formEtudiant(Model model) {
        try {
            model.addAttribute("etudiant", new Etudiant());
            return "add-etudiant";

        } catch (Exception e) {
            return "error";
        }


    }

    @PostMapping(value = "/saveEtudiant")
    public String saveEtudiant(@Valid Etudiant et,
                               BindingResult bindingResult,
                               @RequestParam(name = "picture") MultipartFile file

    ) {
        try {
            if (bindingResult.hasErrors()) {
                System.out.println("bindingResult.hasErrors()" + bindingResult.hasErrors());

                return "add-etudiant";
            }
            // pou n'est pas ecraser le meme nom

            if (!(file.isEmpty())) {
                et.setPhoto(file.getOriginalFilename());

            }
            etudiantRepository.save(et);
            if (!(file.isEmpty())) {
                //photo de la formulaire va etre stocker dans un fichier externe
                System.out.println("(file.isEmpty())" + file.isEmpty());
                System.out.println(" et.getId()" + et.getId());

                System.out.println("Etudiant" + et);
                file.transferTo(new File(imageDir + et.getId()));
                /*file.transferTo(new File(System.getProperty
                        ("user.home") + "/photoStock/" + file.getOriginalFilename()));*/

            }


            System.out.println("Add Etudiant");
            //  etudiantRepository.save(et);
            return "redirect:/etudiant";

        } catch (Exception e) {
            System.out.println("errorrrr");
            return "pagesError/error";
        }

    }

    //display photo
    //la photo envoyer sous format image jpeg
    @RequestMapping(value = "/getPhoto", produces = MediaType.IMAGE_JPEG_VALUE)
    //@ResponseBody resultat va etre envoyer corps de la reponse
    @ResponseBody
    public byte[] getPhoto(Long id) throws Exception {

        File f = new File(imageDir + id);
        return IOUtils.toByteArray(new FileInputStream(f));


    }

    @RequestMapping(value = "/supprimer")
    public String suuprimer(Long id) {
        etudiantRepository.deleteById(id);
        return "redirect:/etudiant";
    }

    @RequestMapping(value = "/modifier")
    public String modifier(Long id, Model model) {
        Etudiant e = etudiantRepository.getOne(id);
        model.addAttribute("etudiant", e);
        return "modifierEtudiant";
    }

    @PostMapping(value = "/updateEtudiant")
    public String modifierEtudiant(@Valid Etudiant et, BindingResult bindingResult, @RequestParam(name = "picture") MultipartFile file
    ) {
        try {
            if (bindingResult.hasErrors()) {
                System.out.println("bindingResult.hasErrors()" + bindingResult.hasErrors());

                return "modifierEtudiant";
            }
            // pou n'est pas ecraser le meme nom

            if (!(file.isEmpty())) {
                et.setPhoto(file.getOriginalFilename());

            }
            etudiantRepository.save(et);
            if (!(file.isEmpty())) {
                //photo de la formulaire va etre stocker dans un fichier externe
                System.out.println("(file.isEmpty())" + file.isEmpty());
                System.out.println(" et.getId()" + et.getId());

                System.out.println("Etudiant" + et);
                file.transferTo(new File(imageDir + et.getId()));
                /*file.transferTo(new File(System.getProperty
                        ("user.home") + "/photoStock/" + file.getOriginalFilename()));*/

            }


            System.out.println("update Etudiant");
            //  etudiantRepository.save(et);
            return "redirect:/etudiant";

        } catch (Exception e) {
            System.out.println("errorrrr");
            return "pagesError/error";
        }

    }
}

