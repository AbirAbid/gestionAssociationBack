package com.proxym.pfe.gestionAssociationBack.missionBenevole.dao;

import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.biens.entities.ParticiperBien;
import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.MissionBenevole;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.ParticiperMissionBenevole;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;

import java.util.List;

public interface ParticiperMissionBenevoleDao {
    List<ParticiperMissionBenevole> findAllParticipationMissionDao();

    List<ParticiperMissionBenevole> findAllByUserUsernameDao(String username);

    ParticiperMissionBenevole saveParticipationMissionDao(ParticiperMissionBenevole participerMissionBenevole);

    void deleteParticipationMissionDao(Long id);

    Boolean existMissionUserDao(MissionBenevole missionBenevole, User user);

    ParticiperMissionBenevole findMissionAndUserDao(MissionBenevole missionBenevole, User user);

    Boolean existParticipationEvenementDao(Evenement evenement);

}
