package com.proxym.pfe.gestionAssociationBack.evenement.services;

import com.proxym.pfe.gestionAssociationBack.evenement.dao.EvenementDao;
import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvenementServiceImp implements EvenementService {

    @Autowired
    EvenementDao evenementDao;


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
}
