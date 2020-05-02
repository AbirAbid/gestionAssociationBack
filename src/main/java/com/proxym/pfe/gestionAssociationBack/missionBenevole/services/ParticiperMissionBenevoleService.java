package com.proxym.pfe.gestionAssociationBack.missionBenevole.services;

import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.MissionBenevole;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.ParticiperMissionBenevole;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;

import java.util.List;

public interface ParticiperMissionBenevoleService {
    List<ParticiperMissionBenevole> findAllParticipationMissionService();

    List<ParticiperMissionBenevole> findAllByUserUsernameService(String username);

    ParticiperMissionBenevole saveParticipationMissionService(ParticiperMissionBenevole participerMissionBenevole);

    void deleteParticipationMissionService(Long id);

    Boolean existMissionUserService(MissionBenevole missionBenevole, User user);

    ParticiperMissionBenevole findMissionAndUserService(MissionBenevole missionBenevole, User user);

    Boolean existParticipationEvenementService(Evenement evenement);

}
