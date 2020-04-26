package com.proxym.pfe.gestionAssociationBack.membre.dao;

import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface MembreDao {
    List<User> getAllUserDao();

    Page<User> findAllMembreDao(PageRequest pageRequest);

    Page<User> rehercherPageMembreDao(String mc, PageRequest pageRequest);

    void supprimerMembreDao(Long id);

    void modifierMembreDao(User user);

  //  User getOneMembreDao(Long id);
    User getOneMembreDao(String id);

}
