package com.proxym.pfe.association.gestion_evenements.services;

import com.proxym.pfe.association.gestion_evenements.dto.EventParticipCount;
import com.proxym.pfe.association.gestion_biens.entities.UserBien;
import com.proxym.pfe.association.gestion_evenements.dto.EvenementDto;
import com.proxym.pfe.association.gestion_evenements.dto.EventCountCategories;
import com.proxym.pfe.association.gestion_evenements.dto.EventCountElmtsDto;
import com.proxym.pfe.association.gestion_evenements.entities.Evenement;
import com.proxym.pfe.association.gestion_missions.entities.UserMission;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;


public interface EvenementService {
    Evenement addEventService(Evenement evenement);

    List<Evenement> listEventService();

    Evenement getOneEventByIdservice(Long id);

    void supprimerEvent(Long id);

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
    void eventDetailService(Model model, Long id) throws ParseException;

}
