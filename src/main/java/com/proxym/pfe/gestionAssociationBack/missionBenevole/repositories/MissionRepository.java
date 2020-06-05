package com.proxym.pfe.gestionAssociationBack.missionBenevole.repositories;

import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findAllByEvenement_Id(Long id);

    List<Mission> findAllByEvenement_Ville(String ville);


}
