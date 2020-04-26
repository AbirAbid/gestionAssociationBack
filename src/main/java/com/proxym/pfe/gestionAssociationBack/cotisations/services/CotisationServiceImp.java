package com.proxym.pfe.gestionAssociationBack.cotisations.services;

import com.proxym.pfe.gestionAssociationBack.cotisations.dao.CotisationDao;
import com.proxym.pfe.gestionAssociationBack.cotisations.entities.Cotisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CotisationServiceImp implements CotisationService {
    @Autowired
    CotisationDao cotisationDao;

    @Override
    public Cotisation addCotisationServ(Cotisation cotisation) {
        return cotisationDao.addCotisationDao(cotisation);
    }

    @Override
    public List<Cotisation> listCotisationServ() {
        return cotisationDao.listCotisationDao();
    }

    @Override
    public Page<Cotisation> findAllPageCotisationServ(PageRequest pageRequest) {
        return cotisationDao.findAllPageCotisationDao(pageRequest);
    }

    @Override
    public Page<Cotisation> rehercherPageCotisationServ(String mc, PageRequest pageRequest) {
        return cotisationDao.rehercherPageCotisationDao(mc, pageRequest);
    }

    @Override
    public void supprimerCotisationServ(Long id) {
        cotisationDao.supprimerCotisation(id);
    }

    @Override
    public void modifierCotisationServ(Cotisation cotisation) {
        cotisationDao.modifierCotisation(cotisation);
    }

    @Override
    public Cotisation getOneServ(Long id) {
        return cotisationDao.getOneDao(id);
    }
}
