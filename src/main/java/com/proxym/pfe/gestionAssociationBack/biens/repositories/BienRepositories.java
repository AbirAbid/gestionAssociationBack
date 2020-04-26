package com.proxym.pfe.gestionAssociationBack.biens.repositories;

import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.sponsors.entities.Sponsor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BienRepositories extends JpaRepository<Bien, Long> {

    // @Query("select  b from Bien b  where b.evenement.id = id ")
    List<Bien> findAllByEvenement_Id(Long id);



}
