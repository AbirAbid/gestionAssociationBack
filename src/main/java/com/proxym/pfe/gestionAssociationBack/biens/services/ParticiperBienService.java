package com.proxym.pfe.gestionAssociationBack.biens.services;

import com.proxym.pfe.gestionAssociationBack.biens.entities.ParticiperBien;

import java.util.List;

public interface ParticiperBienService {

    List<ParticiperBien> findAllParticipationBienService();

    ParticiperBien saveParticipationBienService(ParticiperBien participerBien);

    void deleteParticipationBienService(Long id);
}
