package com.proxym.pfe.gestionAssociationBack.membre.dao;

import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import com.proxym.pfe.gestionAssociationBack.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MembreDaoImp implements MembreDao {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUserDao() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAllMembreDao(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    @Override
    public Page<User> rehercherPageMembreDao(String mc, PageRequest pageRequest) {
        return userRepository.chercherUser(mc, pageRequest);
    }

    @Override
    public void supprimerMembreDao(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public void modifierMembreDao(User user) {
        userRepository.save(user);
    }

    @Override
   /* public User getOneMembreDao(Long id) {
        return userRepository.getOne(id);
    }*/
    public User getOneMembreDao(String id) {
      //  return userRepository.findByEmail(id);
        return userRepository.findByUsername(id);
    }

}
