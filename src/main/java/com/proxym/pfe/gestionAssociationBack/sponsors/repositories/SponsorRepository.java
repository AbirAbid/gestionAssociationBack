package com.proxym.pfe.gestionAssociationBack.sponsors.repositories;

import com.proxym.pfe.gestionAssociationBack.bookPackage.Etudiant.Etudiant;
import com.proxym.pfe.gestionAssociationBack.sponsors.entities.Sponsor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Long> {
    @Query("select  s from Sponsor s  where s.titreSponsor like :x")
    Page<Sponsor> chercherSponsor(@Param("x") String mc, Pageable pageable);
}
