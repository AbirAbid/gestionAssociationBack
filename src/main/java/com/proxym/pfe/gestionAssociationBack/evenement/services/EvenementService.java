package com.proxym.pfe.gestionAssociationBack.evenement.services;

import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;


public interface EvenementService {
    Evenement addEventService(Evenement evenement);

    List<Evenement> listEventService();

    Evenement getOneEventByIdservice(Long id);

    void suuprimerEvent(Long id);

    Page<Evenement> findAllPageEvenementService(PageRequest pageRequest);

    Page<Evenement> rehercherPageEvenementService(String mc, PageRequest pageRequest);


}
