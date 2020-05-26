package com.proxym.pfe.gestionAssociationBack.missionBenevole.services;

import com.proxym.pfe.gestionAssociationBack.missionBenevole.dao.MissionDao;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.Mission;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.UserMission;
import com.proxym.pfe.gestionAssociationBack.user.dao.UserDao;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionServiceImp implements MissionService {
    @Autowired
    MissionDao missionDao;
    @Autowired
    UserDao userDao;

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

    @Override
    public void affecterMission(User user, Mission mission) {

        int index = 0;

        List<UserMission> userMissions = user.getUserMissions();

        while (true) {
            if (userMissions.get(index).getMission().getId() == mission.getId() && index < userMissions.size()) {
                break;
            } else {
                index++;
            }

        }

        System.out.println(" index: after****** " + index);

        userMissions.get(index).setAffected(1);
        System.out.println("userMissions.get(index): " + userMissions.get(index).getMission());

        userDao.saveUserDao(user);
    }

    @Override
    public void libererMission(User user, Mission mission) {
        int index = 0;

        List<UserMission> userMissions = user.getUserMissions();

        while (true) {
            if (userMissions.get(index).getMission().getId() == mission.getId() && index < userMissions.size()) {
                break;
            } else {
                index++;
            }
            System.out.println(" index: " + index);

        }

        System.out.println(" index: after****** " + index);

        userMissions.get(index).setAffected(0);
        System.out.println("userMissions.get(index): " + userMissions.get(index).getMission());

        userDao.saveUserDao(user);

    }
}
