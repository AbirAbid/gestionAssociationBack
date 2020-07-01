package com.proxym.pfe.association.gestion_cotisations.dao;

import com.proxym.pfe.association.gestion_cotisations.entities.Cotisation;
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
