package com.proxym.pfe.gestionAssociationBack.evenement.services;

import com.proxym.pfe.gestionAssociationBack.biens.dao.BienDao;
import com.proxym.pfe.gestionAssociationBack.evenement.dao.EvenementDao;
import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.dao.MissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvenementServiceImp implements EvenementService {

    @Autowired
    EvenementDao evenementDao;

    @Autowired
    BienDao bienDao;
    @Autowired
    MissionDao missionDao;


    @Override
    public Evenement addEventService(Evenement evenement) {
        return evenementDao.addEventDao(evenement);
    }

    @Override
    public List<Evenement> listEventService() {
        System.out.println("***********we are in serv evt*********");

        return evenementDao.listEventDao();
    }

    @Override
    public Evenement getOneEventByIdservice(Long id) {
        return evenementDao.getEventDaoById(id);
    }

    @Override
    public void suuprimerEvent(Long id) {
        bienDao.deleteBienDao(id);
        missionDao.deleteMissionDao(id);
        evenementDao.supprimerEventDao(id);

    }

    @Override
    public Page<Evenement> findAllPageEvenementService(PageRequest pageRequest) {
        return evenementDao.findAllPageEvenementDao(pageRequest);
    }

    @Override
    public Page<Evenement> rehercherPageEvenementService(String mc, PageRequest pageRequest) {
        return evenementDao.rehercherPageEvenementDao(mc, pageRequest);
    }

    @Override
    public Optional<Evenement> findEventByIdService(Long id) {
        return evenementDao.findEventDaoById(id);
    }
}
