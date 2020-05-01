package com.proxym.pfe.gestionAssociationBack.biens.dao;

import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.biens.entities.ParticiperBien;
import com.proxym.pfe.gestionAssociationBack.biens.repositories.ParticiperBienRepositories;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParticiperBienDaoImp implements ParticiperBienDao {
    @Autowired
    ParticiperBienRepositories participerBienRepositories;

    @Override
    public List<ParticiperBien> findAllParticipationBienDao() {
        return participerBienRepositories.findAll();
    }

    @Override
    public List<ParticiperBien> findAllByUser_UsernameDao(String username) {
        return participerBienRepositories.findAllByUser_Username(username);
    }

    @Override
    public ParticiperBien saveParticipationBienDao(ParticiperBien participerBien) {
        return participerBienRepositories.save(participerBien);
    }


    @Override
    public void deleteParticipationBienDao(Long id) {
        participerBienRepositories.deleteById(id);

    }

    @Override
    public Boolean existBienUserDao(Bien b, User user) {
        return participerBienRepositories.existsParticiperBienByBienAndAndUser(b, user);
    }

    @Override
    public ParticiperBien findByBienAndUserDao(Bien bien, User user) {
        return participerBienRepositories.findByBienAndUser(bien, user);
    }
}
