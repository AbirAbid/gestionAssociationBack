package com.proxym.pfe.gestionAssociationBack.evenement.services;

import com.proxym.pfe.gestionAssociationBack.biens.dao.BienDao;
import com.proxym.pfe.gestionAssociationBack.evenement.dao.EvenementDao;
import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.dao.MissionBenevoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvenementServiceImp implements EvenementService {

    @Autowired
    EvenementDao evenementDao;
    @Autowired
    MissionBenevoleDao missionBenevoleDao;
    @Autowired
    BienDao bienDao;


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
        missionBenevoleDao.deleteMissionDao(id);

        evenementDao.supprimerEventDao(id);

    }
}
