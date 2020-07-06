package com.proxym.pfe.association.gestion_sponsors.dao;

import com.proxym.pfe.association.gestion_sponsors.entities.Sponsor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface SponsorDao {
    void saveSponsorDao(Sponsor s);

    List<Sponsor> findAllSponsorDao();
    void supprimerSponsor(Long id);

    void modifierSponsor(Sponsor sp);

    Sponsor getOneDao(Long id);

}
