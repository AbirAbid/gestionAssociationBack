package com.proxym.pfe.association.gestion_biens.repositories;

import com.proxym.pfe.association.gestion_biens.entities.Bien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BienRepositories extends JpaRepository<Bien, Long> {

    List<Bien> findAllByEvenement_Id(Long id);

    List<Bien> findAllByEvenement_Ville(String ville);




}
