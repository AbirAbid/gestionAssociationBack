package com.proxym.pfe.association.gestion_evenements.dao;

import com.proxym.pfe.association.gestion_evenements.entities.Evenement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface EvenementDao {
    Evenement addEventDao(Evenement event);

    List<Evenement> listEventDao();

    Optional<Evenement> findEventDaoById(Long id);

    Evenement getEventDaoById(Long id);

    void supprimerEventDao(Long id);

    Page<Evenement> findAllPageEvenementDao(PageRequest pageRequest);

    Page<Evenement> rehercherPageEvenementDao(String mc, PageRequest pageRequest);
    List<Evenement> findAllByCategorieDao(String categorie);


}
