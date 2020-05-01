package com.proxym.pfe.gestionAssociationBack.biens.repositories;

import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.biens.entities.ParticiperBien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticiperBienRepositories extends JpaRepository<ParticiperBien, Long> {
    List<ParticiperBien> findAllByUser_Username(String username);


}
