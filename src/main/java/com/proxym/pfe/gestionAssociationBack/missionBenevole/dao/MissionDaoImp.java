package com.proxym.pfe.gestionAssociationBack.missionBenevole.dao;

import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.Mission;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.repositories.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MissionDaoImp implements MissionDao {
    @Autowired
    MissionRepository missionRepository;

    @Override
    public void saveAllMissionDao(List<Mission> missions) {

        missionRepository.saveAll(missions.stream().collect(Collectors.toList()));
    }

    @Override
    public List<Mission> findAllMissionDao() {
        return missionRepository.findAll();
    }

    @Override
    public void deleteMissionDao(Long id) {

        missionRepository.deleteAll(missionRepository.findAllByEvenement_Id(id));
    }

    @Override
    public List<Mission> findAllMissionByEventDao(Long id) {
        return missionRepository.findAllByEvenement_Id(id);
    }

    @Override
    public List<Mission> findAllByEvenement_VilleDao(String ville) {
        return missionRepository.findAllByEvenement_Ville(ville);
    }

    @Override
    public Mission saveMissionDao(Mission mission) {
        return missionRepository.save(mission);
    }

    @Override
    public Mission findMissionByIdDao(Long id) {
        return missionRepository.getOne(id);
    }
}
