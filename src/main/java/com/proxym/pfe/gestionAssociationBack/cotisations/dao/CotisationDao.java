package com.proxym.pfe.gestionAssociationBack.cotisations.dao;

import com.proxym.pfe.gestionAssociationBack.cotisations.entities.Cotisation;
import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.sponsors.entities.Sponsor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CotisationDao {

    Cotisation addCotisationDao(Cotisation cotisation);

    List<Cotisation> listCotisationDao();

    Page<Cotisation> findAllPageCotisationDao(PageRequest pageRequest);

    Page<Cotisation> rehercherPageCotisationDao(String mc, PageRequest pageRequest);

    void supprimerCotisation(Long id);

    void modifierCotisation(Cotisation cotisation);

    Cotisation getOneDao(Long id);
}
