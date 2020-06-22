package com.proxym.pfe.gestionAssociationBack.evenement.services;

import com.proxym.pfe.gestionAssociationBack.biens.entities.UserBien;
import com.proxym.pfe.gestionAssociationBack.evenement.dto.EvenementDto;
import com.proxym.pfe.gestionAssociationBack.evenement.dto.EventCountCategories;
import com.proxym.pfe.gestionAssociationBack.evenement.dto.EventCountElmtsDto;
import com.proxym.pfe.gestionAssociationBack.evenement.dto.EventParticipCount;
import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


public interface EvenementService {
    Evenement addEventService(Evenement evenement);

    List<Evenement> listEventService();

    Evenement getOneEventByIdservice(Long id);

    void supprimerEvent(Long id);

    Page<Evenement> findAllPageEvenementService(PageRequest pageRequest);

    Page<Evenement> rehercherPageEvenementService(String mc, PageRequest pageRequest);

    Optional<Evenement> findEventByIdService(Long id);

    void AjouterEvent(@Valid EvenementDto evenementDto);

    EvenementDto formulaireUpdate(Long id);

    void ModifierEvent(@Valid EvenementDto evenementDto);

    EventCountElmtsDto getElementNumber();
    List<Evenement> findAllByCategorieService(String categorie);

    List<UserBien> getListDonneursByEvent(Evenement evenement);
    List<UserMission> getListBenevolesByEvent(Evenement evenement);

    void TauxEchangeForAllUser();

    List<EventCountCategories> countCategEvent();
    List<EventCountCategories> countPartByCategorieEvent();
    List<EventParticipCount>countPartByEvent();
    void eventDetailService(Model model, Long id);

}
