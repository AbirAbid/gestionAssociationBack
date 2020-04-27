package com.proxym.pfe.gestionAssociationBack.evenement.services;

import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;

import java.util.List;


public interface EvenementService {
    Evenement addEventService(Evenement evenement);

    List<Evenement> listEventService();

    Evenement getOneEventByIdservice(Long id);

    void suuprimerEvent(Long id);
}
