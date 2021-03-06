package com.proxym.pfe.association.gestion_cotisations.services;

import com.proxym.pfe.association.gestion_cotisations.dto.CotisationDto;
import com.proxym.pfe.association.gestion_cotisations.entities.Cotisation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CotisationService {

    Cotisation addCotisationServ(CotisationDto cotisationDto);
    Cotisation updateCotisationServ(Cotisation cotisation);

    List<Cotisation> listCotisationServ();


    void supprimerCotisationServ(Long id);


    Cotisation getOneServ(Long id);
}
