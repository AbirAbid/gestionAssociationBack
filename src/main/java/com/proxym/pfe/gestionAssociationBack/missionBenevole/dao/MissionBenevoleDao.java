package com.proxym.pfe.gestionAssociationBack.missionBenevole.dao;

import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.MissionBenevole;

import java.util.List;

public interface MissionBenevoleDao {
    void saveAllMissionBenDao(List<MissionBenevole> missionBenevoles);

    List<MissionBenevole> findAllMissionBenDao();

    void deleteMissionDao(Long id);

    List<MissionBenevole> findAllMissionByEventDao(Long id);


}
