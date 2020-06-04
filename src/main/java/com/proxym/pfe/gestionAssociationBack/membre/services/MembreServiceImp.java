package com.proxym.pfe.gestionAssociationBack.membre.services;

import com.proxym.pfe.gestionAssociationBack.membre.dao.MembreDao;
import com.proxym.pfe.gestionAssociationBack.user.dao.UserDao;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembreServiceImp implements MembreService {
    @Autowired
    MembreDao membreDao;

    @Override
    public List<User> getAllMembreService() {
        return membreDao.getAllMembreDao();
    }

    @Override
    public Page<User> findAllMembreService(PageRequest pageRequest) {
        return membreDao.findAllMembreDao(pageRequest);
    }

    @Override
    public Page<User> rehercherPageMembreService(String mc, PageRequest pageRequest) {
        return membreDao.rehercherPageMembreDao(mc, pageRequest);
    }

    @Override
    public void supprimerMembreService(Long id) {
        membreDao.supprimerMembreDao(id);
    }

    @Override
    public void modifierMembreService(User user) {
        membreDao.modifierMembreDao(user);
    }

    @Override
    public User getOneMembreService(String id) {
        return membreDao.getOneMembreDao(id);
    }
}
