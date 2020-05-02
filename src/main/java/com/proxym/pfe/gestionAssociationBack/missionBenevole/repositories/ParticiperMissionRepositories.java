package com.proxym.pfe.gestionAssociationBack.missionBenevole.repositories;

import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.MissionBenevole;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.ParticiperMissionBenevole;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticiperMissionRepositories extends JpaRepository<ParticiperMissionBenevole, Long> {
    List<ParticiperMissionBenevole> findAllByUser_Username(String username);

    Boolean existsParticiperMissionBenevoleByMissionBenevoleAndUser(MissionBenevole missionBenevole, User user);

    Boolean existsParticiperMissionBenevoleByMissionBenevole_Evenement(Evenement evenement);

    ParticiperMissionBenevole findByMissionBenevoleAndUser(MissionBenevole missionBenevole, User user);
}
