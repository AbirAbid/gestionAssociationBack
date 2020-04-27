package com.proxym.pfe.gestionAssociationBack.evenement.dao;

import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EvenementDao {
    Evenement addEventDao(Evenement event);

    List<Evenement> listEventDao();

    Evenement getEventDaoById(Long id);

    void supprimerEventDao(Long id);
}
