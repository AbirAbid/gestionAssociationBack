package com.proxym.pfe.association.gestion_cotisations.repositories;

import com.proxym.pfe.association.gestion_cotisations.entities.Cotisation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CotisationRepository extends JpaRepository<Cotisation, Long> {
    @Query("select  c from Cotisation c  where c.cotisationName like :x")
    Page<Cotisation> chercherCotisation(@Param("x") String mc, Pageable pageable);
}
