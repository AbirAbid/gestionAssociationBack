package com.proxym.pfe.association.gestion_cotisations.services;

import com.proxym.pfe.association.gestion_cotisations.dao.CotisationDao;
import com.proxym.pfe.association.gestion_cotisations.dto.CotisationDto;
import com.proxym.pfe.association.gestion_cotisations.entities.Cotisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CotisationServiceImp implements CotisationService {
    @Autowired
    CotisationDao cotisationDao;

    @Override
    public Cotisation addCotisationServ(CotisationDto cotisationDto) {
        Cotisation cotisation1 = new Cotisation();
        cotisation1.setCotisationName(cotisationDto.getCotisationName());
        cotisation1.setDateDebut(cotisationDto.getDateDebut());
        cotisation1.setMontant(cotisationDto.getMontant());
        cotisation1.setDateFin(cotisationDto.getDateFin());
        cotisationDao.addCotisationDao(cotisation1);
        return cotisationDao.addCotisationDao(cotisation1);
    }

    @Override
    public Cotisation updateCotisationServ(Cotisation cotisation) {
        return cotisationDao.addCotisationDao(cotisation);
    }

    @Override
    public List<Cotisation> listCotisationServ() {
        return cotisationDao.listCotisationDao();
    }



    @Override
    public void supprimerCotisationServ(Long id) {
        cotisationDao.supprimerCotisation(id);
    }


    @Override
    public Cotisation getOneServ(Long id) {
        return cotisationDao.getOneDao(id);
    }
}
