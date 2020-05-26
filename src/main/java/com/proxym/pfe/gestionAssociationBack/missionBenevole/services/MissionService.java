package com.proxym.pfe.gestionAssociationBack.missionBenevole.services;

import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.Mission;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.UserMission;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;

import java.util.List;

public interface MissionService {
    void saveAllMissionService(List<Mission> missions);

    Mission saveMissionService(Mission mission);

    List<Mission> findAllMissionService();

    List<Mission> findAllMissionByEventService(Long id);

    List<Mission> findAllByEvenement_VilleService(String ville);

    Mission findMissionByIdService(Long id);

    void affecterMission(User user, Mission mission );
    void libererMission(User user, Mission mission );

}
