package com.proxym.pfe.association.gestion_biens.models.dao;

import com.proxym.pfe.association.gestion_biens.models.entities.Bien;

import java.util.List;

public interface BienDao {
    List<Bien> findAllDao();
    Bien saveBienDao(Bien bien);
    void saveAllDao(List<Bien> biens);
    List<Bien> findAllByEventDao(Long id);
    List<Bien> findAllByEvenement_VilleDao(String ville);
    void deleteBienDao(Long id);
}
