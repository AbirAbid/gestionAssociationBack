package com.proxym.pfe.association.gestion_cotisations.dao;

import com.proxym.pfe.association.gestion_cotisations.entities.Cotisation;
import java.util.List;

public interface CotisationDao {

    Cotisation addCotisationDao(Cotisation cotisation);

    List<Cotisation> listCotisationDao();

    void supprimerCotisation(Long id);
    Cotisation getOneDao(Long id);
}
