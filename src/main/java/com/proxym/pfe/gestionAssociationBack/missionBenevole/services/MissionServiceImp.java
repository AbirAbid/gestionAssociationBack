package com.proxym.pfe.gestionAssociationBack.missionBenevole.services;

import com.proxym.pfe.gestionAssociationBack.missionBenevole.dao.MissionDao;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.Mission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionServiceImp implements MissionService {
    @Autowired
    MissionDao missionDao;

    @Override
    public void saveAllMissionService(List<Mission> missions) {
        missionDao.saveAllMissionDao(missions);
    }

    @Override
    public Mission saveMissionService(Mission mission) {
        return missionDao.saveMissionDao(mission);
    }

    @Override
    public List<Mission> findAllMissionService() {
        return missionDao.findAllMissionDao();
    }

    @Override
    public List<Mission> findAllMissionByEventService(Long id) {
        return missionDao.findAllMissionByEventDao(id);
    }

    @Override
    public List<Mission> findAllByEvenement_VilleService(String ville) {

        return missionDao.findAllByEvenement_VilleDao(ville);
    }

    @Override
    public Mission findMissionByIdService(Long id) {
        return missionDao.findMissionByIdDao(id);
    }
}
