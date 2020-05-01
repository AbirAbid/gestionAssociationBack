package com.proxym.pfe.gestionAssociationBack.biens.dao;

import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.biens.entities.ParticiperBien;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;

import java.util.List;

public interface ParticiperBienDao {
    List<ParticiperBien> findAllParticipationBienDao();

    List<ParticiperBien> findAllByUser_UsernameDao(String username);

    ParticiperBien saveParticipationBienDao(ParticiperBien participerBien);

    void deleteParticipationBienDao(Long id);

    Boolean existBienUserDao(Bien b, User user);
    ParticiperBien findByBienAndUserDao(Bien bien, User user);


    //List<Bien> findAllByEventDao(Long id);

    // List<Bien> findAllByEvenement_VilleDao(String ville);

    // List<Bien> findAllByEventIdDao(Long id);
}
