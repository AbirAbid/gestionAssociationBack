package com.proxym.pfe.association.gestion_missions.dao;

import com.proxym.pfe.association.gestion_missions.entities.Mission;
import com.proxym.pfe.association.gestion_missions.repositories.MissionRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MissionDaoImp implements MissionDao {
    @Autowired
    MissionRepositories missionRepositories;

    @Override
    public void saveAllMissionDao(List<Mission> missions) {

        missionRepositories.saveAll(missions.stream().collect(Collectors.toList()));
    }

    @Override
    public List<Mission> findAllMissionDao() {
        return missionRepositories.findAll();
    }

    @Override
    public void deleteMissionDao(Long id) {

        missionRepositories.deleteAll(missionRepositories.findAllByEvenement_Id(id));
    }

    @Override
    public List<Mission> findAllMissionByEventDao(Long id) {
        return missionRepositories.findAllByEvenement_Id(id);
    }

    @Override
    public List<Mission> findAllByEvenement_VilleDao(String ville) {
        return missionRepositories.findAllByEvenement_Ville(ville);
    }

    @Override
    public Mission saveMissionDao(Mission mission) {
        return missionRepositories.save(mission);
    }

    @Override
    public Mission findMissionByIdDao(Long id) {
        return missionRepositories.getOne(id);
    }

}
