package com.proxym.pfe.gestionAssociationBack.missionBenevole.services;

import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.Mission;

import java.util.List;

public interface MissionService {
    void saveAllMissionService(List<Mission> missions);

    Mission saveMissionService(Mission mission);

    List<Mission> findAllMissionService();
   List<Mission> findAllMissionByEventService(Long id);

    List<Mission> findAllByEvenement_VilleService(String ville);
}
