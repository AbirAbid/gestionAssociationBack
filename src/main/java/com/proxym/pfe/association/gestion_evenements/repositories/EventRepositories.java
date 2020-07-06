package com.proxym.pfe.association.gestion_evenements.repositories;

import com.proxym.pfe.association.gestion_evenements.entities.Evenement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepositories extends JpaRepository<Evenement, Long> {

    List<Evenement> findAllByCategorie(String categorie);


}
