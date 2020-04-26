package com.proxym.pfe.gestionAssociationBack.biens.services;

import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.bookPackage.Book;

import java.util.List;
import java.util.Optional;

public interface BienService {

    List<Bien> findAllService();

    void saveAllService(List<Bien> biens);

    void editAllService(List<Bien> biens);

    List<Bien> findAllByEventService(Long id);

}
