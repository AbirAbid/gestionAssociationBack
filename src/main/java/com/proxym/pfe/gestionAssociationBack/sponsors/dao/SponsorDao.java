package com.proxym.pfe.gestionAssociationBack.sponsors.dao;

import com.proxym.pfe.gestionAssociationBack.sponsors.entities.Sponsor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface SponsorDao {
    void saveSponsorDao(Sponsor s);

    List<Sponsor> findAllSponsorDao();

    Page<Sponsor> findAllPageSponsorDao(PageRequest pageRequest);

    Page<Sponsor> rehercherPageSponsorDao(String mc, PageRequest pageRequest);

    void supprimerSponsor(Long id);

    void modifierSponsor(Sponsor sp);

    Sponsor getOneDao(Long id);


}
