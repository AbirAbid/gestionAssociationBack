package com.proxym.pfe.gestionAssociationBack.missionBenevole.repositories;

import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.MissionBenevole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionBenevoleRepositories extends JpaRepository<MissionBenevole, Long> {
    List<MissionBenevole> findAllByEvenement_Id(Long id);
}
