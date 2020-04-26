package com.proxym.pfe.gestionAssociationBack.missionBenevole.services;

import com.proxym.pfe.gestionAssociationBack.missionBenevole.dao.MissionBenevoleDao;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.MissionBenevole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionBenevoleServiceImp implements MissionBenevoleService {
    @Autowired
    MissionBenevoleDao missionBenevoleDao;


    @Override
    public void saveAllMissionService(List<MissionBenevole> missionBenevoles) {
        System.out.println("missionBenevoles" + missionBenevoles);
        missionBenevoleDao.saveAllMissionBenDao(missionBenevoles);
    }

    @Override
    public List<MissionBenevole> findAllMissionService() {
        return missionBenevoleDao.findAllMissionBenDao();
    }
}
