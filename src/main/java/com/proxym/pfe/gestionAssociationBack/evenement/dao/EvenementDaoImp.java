package com.proxym.pfe.gestionAssociationBack.evenement.dao;

import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.evenement.repositories.EventRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
    public Optional<Evenement> findEventDaoById(Long id) {
        return eventRepositories.findById(id);
    }

    @Override
    public Evenement getEventDaoById(Long id) {
        return eventRepositories.getOne(id);
    }

    @Override
    public void supprimerEventDao(Long id) {

        eventRepositories.deleteById(id);
    }

    @Override
    public Page<Evenement> findAllPageEvenementDao(PageRequest pageRequest) {

        return eventRepositories.findAll(pageRequest);
    }

    @Override
    public Page<Evenement> rehercherPageEvenementDao(String mc, PageRequest pageRequest) {
        return eventRepositories.chercherEvenement(mc, pageRequest);
    }

    @Override
    public List<Evenement> findAllByCategorieDao(String categorie) {
        return eventRepositories.findAllByCategorie(categorie);
    }


}
