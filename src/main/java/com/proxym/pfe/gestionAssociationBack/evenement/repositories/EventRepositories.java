package com.proxym.pfe.gestionAssociationBack.evenement.repositories;

import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.sponsors.entities.Sponsor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepositories extends JpaRepository<Evenement, Long> {
    List<Evenement> findByTitre(String titre);

    @Query("select  e from Evenement e  where e.titre like :x")
    Page<Evenement> chercherEvenement(@Param("x") String mc, Pageable pageable);

}
