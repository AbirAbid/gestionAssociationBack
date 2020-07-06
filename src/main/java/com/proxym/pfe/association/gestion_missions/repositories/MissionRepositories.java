package com.proxym.pfe.association.gestion_missions.repositories;

import com.proxym.pfe.association.gestion_missions.entities.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissionRepositories extends JpaRepository<Mission, Long> {
    List<Mission> findAllByEvenement_Id(Long id);

    List<Mission> findAllByEvenement_Ville(String ville);


}
