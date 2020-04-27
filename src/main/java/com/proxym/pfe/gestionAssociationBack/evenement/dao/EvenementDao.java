package com.proxym.pfe.gestionAssociationBack.evenement.dao;

import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.sponsors.entities.Sponsor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface EvenementDao {
    Evenement addEventDao(Evenement event);

    List<Evenement> listEventDao();

    Evenement getEventDaoById(Long id);

    void supprimerEventDao(Long id);

    Page<Evenement> findAllPageEvenementDao(PageRequest pageRequest);

    Page<Evenement> rehercherPageEvenementDao(String mc, PageRequest pageRequest);


}
