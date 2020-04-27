package com.proxym.pfe.gestionAssociationBack.evenement.dao;

import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.evenement.repositories.EventRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public class EvenementDaoImp implements EvenementDao {
    @Autowired
    EventRepositories eventRepositories;


    @Override
    public Evenement addEventDao(Evenement event) {
        try {
            return eventRepositories.save(event);

        } catch (Exception e) {
            System.out.println("ExcepDaoEvt" + e);
            return null;
        }
    }

    @Override
    public List<Evenement> listEventDao() {
        try {
            System.out.println("***********we are in DAO evt*********");
            return eventRepositories.findAll();

        } catch (Exception e) {
            System.out.println("ExcepDaoEvt" + e);
            return null;
        }
    }

    @Override
    public Evenement getEventDaoById(Long id) {
        return eventRepositories.getOne(id);
    }

    @Override
    public void supprimerEventDao(Long id) {

        eventRepositories.deleteById(id);
    }


}
