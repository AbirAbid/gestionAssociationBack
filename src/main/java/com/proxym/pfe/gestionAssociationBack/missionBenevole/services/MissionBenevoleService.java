package com.proxym.pfe.gestionAssociationBack.missionBenevole.services;

import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.MissionBenevole;

import java.util.List;

public interface MissionBenevoleService {

    void saveAllMissionService(List<MissionBenevole> missionBenevoles);

    List<MissionBenevole> findAllMissionService();

    List<MissionBenevole> findAllMissionByEventService(Long id);
    List<MissionBenevole> findAllByEvenement_VilleService(String ville);


}
