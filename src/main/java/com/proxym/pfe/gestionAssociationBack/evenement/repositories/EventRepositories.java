package com.proxym.pfe.gestionAssociationBack.evenement.repositories;

import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepositories extends JpaRepository<Evenement, Long> {
    List<Evenement> findByTitre(String titre);

}
