package com.proxym.pfe.gestionAssociationBack.missionBenevole.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proxym.pfe.gestionAssociationBack.evenement.dao.EvenementDao;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.dao.MissionDao;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.dto.ParticiperMissionDto;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.Mission;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.MissionUserDisplay;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.UserMission;
import com.proxym.pfe.gestionAssociationBack.user.dao.UserDao;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MissionServiceImp implements MissionService {
    @Autowired
    MissionDao missionDao;
    @Autowired
    UserDao userDao;
    @Autowired
    EvenementDao evenementDao;

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

    @Override
    public void participerMissionRest(ParticiperMissionDto participerMissionDto, User user) {
        UserMission userMission = new UserMission();

        System.out.println("******************** participerMissionDto *******************" + participerMissionDto);
        System.out.println("******************** user *******************" + user);

        // System.out.println("******************** user *******************" + user);
        userMission.setUser(user);
        Mission mission = participerMissionDto.getMission();
        mission.getEvenement().setActive(1);
        userMission.setMission(mission);
        userMission.setAffected(0);
        userMission.setEnAttente(1);
        userMission.setDemandeDate(participerMissionDto.getDemandeDate());
        System.out.println("******************** userMission *******************" + userMission);
        user.getUserMissions().add(userMission);
        System.out.println("**************  user.getUserMissions().size()**********" + user.getUserMissions().size());
        //missionService.saveMissionService(mission);
        evenementDao.addEventDao(mission.getEvenement());
        missionDao.saveMissionDao(mission);
        userDao.saveUserDao(user);


    }

    /***methode get allParicipation By user****/
    @Override
    public List<UserMission> getListMissionUserRest(String username) throws IOException {

        User user = userDao.findByUsernameDao(username);
        List<UserMission> userMissions = user.getUserMissions();
        ObjectMapper mapper = new ObjectMapper();

        List<UserMission> userMissions1 = new ArrayList<>();
        for (int i = 0; i < userMissions.size(); i++) {
            System.out.println("userMissions" + userMissions.get(i).getMission().getTitre());

            UserMission userMission = mapper.readValue(mapper.writeValueAsString(userMissions.get(i)), UserMission.class);

            userMissions1.add(userMission);


        }
        return userMissions1;


    }

    /**************Methode  get UseMission By event id ***/
    @Override

    public List<UserMission> getUserMissionByEventRest(String username, Long id) throws IOException {

        List<UserMission> userMissions = getListMissionUserRest(username);
        List<UserMission> userMissionsByIdEvt = new ArrayList<>();
        for (int i = 0; i < userMissions.size(); i++) {
            if (userMissions.get(i).getMission().getEvenement().getId() == id) {
                userMissionsByIdEvt.add(userMissions.get(i));
            }
        }
        return userMissionsByIdEvt;
    }


    /***methode get MissionUserDisplay FOR user ByEvent****/
    @Override
    public List<MissionUserDisplay> getListMissionUserDisplayRest(String username, Long id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<UserMission> userMissions = getUserMissionByEventRest(username, id);
        List<Mission> missions = missionDao.findAllMissionByEventDao(id);


        List<MissionUserDisplay> missionUserDisplays = new ArrayList<>();

//pour éliminer redondance
        for (int i = 0; i < missions.size(); i++) {
            MissionUserDisplay missionUserDisplay = new MissionUserDisplay();
            missionUserDisplay.setMission(missions.get(i));
            for (int j = 0; j < userMissions.size(); j++) {
                boolean exist = userMissions.get(j).getMission().getId() == missions.get(i).getId();
                if (exist) {


                    missionUserDisplay.setExist(1);
                    missionUserDisplay.setAffected(userMissions.get(j).getAffected());

                    break;

                } else {
                    missionUserDisplay.setExist(0);
                    missionUserDisplay.setAffected(0);

                }

            }
            missionUserDisplays.add(missionUserDisplay);


        }


        List<MissionUserDisplay> missionUserDisplays2 = new ArrayList<>();
        for (int i = 0; i < missionUserDisplays.size(); i++) {

            MissionUserDisplay missionUserDisplay = mapper.readValue(mapper.writeValueAsString(missionUserDisplays.get(i)), MissionUserDisplay.class);

            missionUserDisplays2.add(missionUserDisplay);


        }

        return missionUserDisplays2;
    }

    @Override
    public void libererMissionRest(String username, Long id) {
        User user = userDao.findByUsernameDao(username);
        Mission mission = missionDao.findMissionByIdDao(id);
        List<UserMission> userMissions = user.getUserMissions();
        int index = 0;

        while (true) {
            if (userMissions.get(index).getMission().getId() == mission.getId() && index < userMissions.size()) {
                break;
            } else {
                index++;
            }

        }

        userMissions.remove(index);
        userDao.saveUserDao(user);
    }



}
