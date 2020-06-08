package com.proxym.pfe.gestionAssociationBack.cotisations.services;

import com.proxym.pfe.gestionAssociationBack.cotisations.dto.CotisationDto;
import com.proxym.pfe.gestionAssociationBack.cotisations.entities.Cotisation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CotisationService {

    Cotisation addCotisationServ(CotisationDto cotisationDto);
    Cotisation updateCotisationServ(Cotisation cotisation);

    List<Cotisation> listCotisationServ();


    Page<Cotisation> findAllPageCotisationServ(PageRequest pageRequest);

    Page<Cotisation> rehercherPageCotisationServ(String mc, PageRequest pageRequest);

    void supprimerCotisationServ(Long id);

    void modifierCotisationServ(Cotisation cotisation);

    Cotisation getOneServ(Long id);
}
