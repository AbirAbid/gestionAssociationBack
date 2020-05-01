package com.proxym.pfe.gestionAssociationBack.biens.services;

import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.biens.entities.ParticiperBien;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;

import java.util.List;

public interface ParticiperBienService {
    List<ParticiperBien> findAllByUser_UsernameService(String username);

    List<ParticiperBien> findAllParticipationBienService();

    ParticiperBien saveParticipationBienService(ParticiperBien participerBien);

    void deleteParticipationBienService(Long id);

    Boolean existBienUserService(Bien b, User user);

    ParticiperBien findByBienAndUserService(Bien bien, User user);

}
