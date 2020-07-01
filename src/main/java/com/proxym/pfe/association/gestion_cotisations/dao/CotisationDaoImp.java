package com.proxym.pfe.association.gestion_cotisations.dao;

import com.proxym.pfe.association.gestion_cotisations.entities.Cotisation;
import com.proxym.pfe.association.gestion_cotisations.repositories.CotisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CotisationDaoImp implements CotisationDao {
    @Autowired
    CotisationRepository cotisationRepository;

    @Override
    public Cotisation addCotisationDao(Cotisation cotisation) {
        return cotisationRepository.save(cotisation);
    }

    @Override
    public List<Cotisation> listCotisationDao() {
        return cotisationRepository.findAll();
    }

    @Override
    public Page<Cotisation> findAllPageCotisationDao(PageRequest pageRequest) {
        return cotisationRepository.findAll(pageRequest);
    }

    @Override
    public Page<Cotisation> rehercherPageCotisationDao(String mc, PageRequest pageRequest) {
        return cotisationRepository.chercherCotisation(mc, pageRequest);
    }

    @Override
    public void supprimerCotisation(Long id) {
        cotisationRepository.deleteById(id);

    }

    @Override
    public void modifierCotisation(Cotisation cotisation) {
        cotisationRepository.save(cotisation);
    }

    @Override
    public Cotisation getOneDao(Long id) {
        return cotisationRepository.getOne(id);
    }
}
