package com.proxym.pfe.gestionAssociationBack.membre.services;

import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface MembreService {
    List<User> getAllMembreService();

    Page<User> findAllMembreService(PageRequest pageRequest);

    Page<User> rehercherPageMembreService(String mc, PageRequest pageRequest);

    void supprimerMembreService(Long id);

    void modifierMembreService(User user);

    User getOneMembreService(String id);
}
