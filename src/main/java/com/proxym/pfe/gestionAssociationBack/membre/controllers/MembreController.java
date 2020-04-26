package com.proxym.pfe.gestionAssociationBack.membre.controllers;

import com.proxym.pfe.gestionAssociationBack.GestionAssociationBackApplication;
import com.proxym.pfe.gestionAssociationBack.cotisations.entities.Cotisation;
import com.proxym.pfe.gestionAssociationBack.membre.services.MembreService;
import com.proxym.pfe.gestionAssociationBack.user.entities.Role;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.proxym.pfe.gestionAssociationBack.user.entities.RoleName;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/membres/")
public class MembreController {
    @Autowired
    MembreService membreService;

    @GetMapping(value = "listeMembres")
    public String showList(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "motCle", defaultValue = "") String mc) {
        try {
            Page<User> pagesMembresRech = membreService.rehercherPageMembreService("%" + mc + "%", new PageRequest(page, 5));
            //Page<User> pagesMembres = membreService.findAllMembreService(new PageRequest(page, 5));
            //pour compter nombre des pages************
            int pagesCount = pagesMembresRech.getTotalPages();
            int[] pages = new int[pagesCount];

            for (int i = 0; i < pagesCount; i++) {
                pages[i] = i;
                System.out.println(" pages[i] " + pages[i]);
            }
            /*List<User> MembresList = membreService.getAllMembreService();
            System.out.println("MembresList****** " + MembresList);
            List<User> MembresListUser = new ArrayList<>();
            for (int i = 0; i < MembresList.size(); i++) {
                Stream<Role> roleStream = MembresList.get(i).getRoles().stream();
                List<String> collect = roleStream.map(x -> x.getName().name()).collect(Collectors.toList());
                System.out.println("collect***" + i + "* *****" + collect.get(0).equals("ROLE_USER"));
                if (collect.get(0).equals("ROLE_USER")) {
                    MembresListUser.add(MembresList.get(i));
                }
            }
            //  Page<User> pagess = new PageImpl(MembresListUser);
            int count = 5;
            String sortOrder = "desc";
            String sortBy = "id";
            Sort sort = new Sort(Sort.Direction.fromString(sortOrder), sortBy);
            PageRequest pageable = new PageRequest(page, count, sort);
            /*if (a > b) {max = a;}else {max = b;}*/
            /*max = (a > b) ? a : b;*/
           /* int max = (count * (page + 1) > MembresListUser.size()) ? MembresListUser.size() : count * (page + 1);
            Page<User> pagess = new PageImpl<User>(MembresListUser.subList(page * count, max),pageable, MembresListUser.size());
            //Page<User> pagess = new PageImpl(MembresListUser, new PageRequest(page, 5,sort), MembresListUser.size());

            int pagesCount = pagess.getTotalPages();
            int[] pages = new int[pagesCount];
            for (int i = 0; i < pagesCount; i++) {
                pages[i] = i;
                System.out.println(" pages[i] " + pages[i]);
            }*/
            /*System.out.println("pagess.getSize()");
            Set<Role> roles = pagesMembresRech.getContent().get(pagesMembresRech.getContent().size() - 1).getRoles();
            Stream<Role> sp = pagesMembresRech.getContent().get(pagesMembresRech.getContent().size() - 1).getRoles().stream();
            List<String> ld = sp.map(x -> x.getName().name()).collect(Collectors.toList());
            System.out.println("ld***" + ld);
            System.out.println("pagesMembresRech.getContent().get(2).getRoles().iterator() "
                    + pagesMembresRech.getContent().get(1).getRoles().toArray().getClass());
            System.out.println("pagesMembresRech.getContent().get(2).getRoles().toArray() "
                    + pagesMembresRech.getContent());
*/
            //**********************
            model.addAttribute("pageCourante", page);
            model.addAttribute("pageContent", pagesMembresRech.getContent());
            model.addAttribute("mc", mc);
            model.addAttribute("pages", pages);
            model.addAttribute("pagesMembres", pagesMembresRech);

            return "membres/membresListe";
        } catch (Exception e) {
            System.out.println(e);
            return "pagesError/error";
        }


    }

    @RequestMapping(value = "/membreDetailUrl")
    public String membreDetail(Model model, String id) {
      /*  Cotisation cotisation = cotisationService.getOneServ(id);
        model.addAttribute("cotisation", cotisation);*/
      try {
          User user = membreService.getOneMembreService(id);
          System.out.println(user);
          model.addAttribute("membre", user);
          return "membres/membreDetail";
      } catch (Exception e){  return "pagesError/error" ;}


    }
}
