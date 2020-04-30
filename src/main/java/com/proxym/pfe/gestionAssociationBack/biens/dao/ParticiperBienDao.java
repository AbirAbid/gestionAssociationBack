package com.proxym.pfe.gestionAssociationBack.biens.dao;

import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.biens.entities.ParticiperBien;

import java.util.List;

public interface ParticiperBienDao {
    List<ParticiperBien> findAllParticipationBienDao();

    ParticiperBien saveParticipationBienDao(ParticiperBien participerBien);

    void deleteParticipationBienDao(Long id);


    //List<Bien> findAllByEventDao(Long id);

    // List<Bien> findAllByEvenement_VilleDao(String ville);

    // List<Bien> findAllByEventIdDao(Long id);
}
