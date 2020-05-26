package com.proxym.pfe.gestionAssociationBack.evenement.services;

import com.proxym.pfe.gestionAssociationBack.evenement.dto.EvenementDto;
import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


public interface EvenementService {
    Evenement addEventService(Evenement evenement);

    List<Evenement> listEventService();

    Evenement getOneEventByIdservice(Long id);

    void suuprimerEvent(Long id);

    Page<Evenement> findAllPageEvenementService(PageRequest pageRequest);

    Page<Evenement> rehercherPageEvenementService(String mc, PageRequest pageRequest);

    Optional<Evenement> findEventByIdService(Long id);
    void AjouterEvent(@Valid EvenementDto evenementDto);
    EvenementDto formulaireUpdate(Long id);

}
