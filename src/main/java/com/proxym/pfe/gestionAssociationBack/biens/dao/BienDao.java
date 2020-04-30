package com.proxym.pfe.gestionAssociationBack.biens.dao;

import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;

import java.util.List;

public interface BienDao {
    List<Bien> findAllDao();

    Bien saveBienDao(Bien bien);

    void saveAllDao(List<Bien> biens);

    void editAllDao(List<Bien> biens);

    List<Bien> findAllByEventDao(Long id);

    List<Bien> findAllByEvenement_VilleDao(String ville);

    // List<Bien> findAllByEventIdDao(Long id);
    void deleteBienDao(Long id);
}
