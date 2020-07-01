package com.proxym.pfe.association.gestion_missions.dao;

import com.proxym.pfe.association.gestion_missions.entities.Mission;

import java.util.List;

public interface MissionDao {
    void saveAllMissionDao(List<Mission> missions);

    List<Mission> findAllMissionDao();

    void deleteMissionDao(Long id);

    List<Mission> findAllMissionByEventDao(Long id);

    List<Mission> findAllByEvenement_VilleDao(String ville);

    Mission saveMissionDao(Mission mission);

    Mission findMissionByIdDao(Long id);

}
