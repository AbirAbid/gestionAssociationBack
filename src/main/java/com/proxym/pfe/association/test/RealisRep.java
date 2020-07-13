package com.proxym.pfe.association.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealisRep extends JpaRepository<Realisateur, Long> {
}
