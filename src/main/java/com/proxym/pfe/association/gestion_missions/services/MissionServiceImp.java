package com.proxym.pfe.association.gestion_missions.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proxym.pfe.association.gestion_biens.models.entities.Bien;
import com.proxym.pfe.association.gestion_evenements.dao.EvenementDao;
import com.proxym.pfe.association.gestion_missions.dao.MissionDao;
import com.proxym.pfe.association.gestion_missions.dto.ParticiperMissionDto;
import com.proxym.pfe.association.gestion_missions.dto.UserMissionDto;
import com.proxym.pfe.association.gestion_missions.entities.Mission;
import com.proxym.pfe.association.gestion_missions.dto.MissionUserDisplay;
import com.proxym.pfe.association.gestion_missions.entities.UserMission;
import com.proxym.pfe.association.gestion_utilisateurs.dao.UserDao;
import com.proxym.pfe.association.gestion_utilisateurs.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class MissionServiceImp implements MissionService {
    @Autowired
    MissionDao missionDao;
    @Autowired
    UserDao userDao;
    @Autowired
    EvenementDao evenementDao;


    @Override
    public List<Mission> findAllMissionService() throws IOException {


        return ConvertLisMissionToJson( missionDao.findAllMissionDao());
    }

    @Override
    public List<Mission> findAllMissionByEventService(Long id) throws IOException {


        return ConvertLisMissionToJson(missionDao.findAllMissionByEventDao(id));
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
    public void affecterMission(String username, Long id) {

        User user = userDao.findByUsernameDao(username);
        Mission mission = missionDao.findMissionByIdDao(id);

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
    public void libererMission(String username, Long id) {

        User user = userDao.findByUsernameDao(username);
        Mission mission = missionDao.findMissionByIdDao(id);

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

        userMission.setUser(user);
        Mission mission = participerMissionDto.getMission();
        mission.getEvenement().setActive(1);
        userMission.setMission(mission);
        userMission.setAffected(0);
        userMission.setEnAttente(1);
        userMission.setDemandeDate(participerMissionDto.getDemandeDate());
        userMission.setDateDisponibiliteList(participerMissionDto.getDateDisponibiliteList());
        user.getUserMissions().add(userMission);
        //missionService.saveMissionService(mission);

        /*If association bidirect*/
        evenementDao.addEventDao(mission.getEvenement());
        missionDao.saveMissionDao(mission);
        /**/
        userDao.saveUserDao(user);


    }

    @Override
    public List<UserMission> getListMissionUser() {
        List<User> users = userDao.getAllBenevolesDao();
        //    System.out.println("users with duplicate  "+ users);
        //eliminer les doublons
        Set<User> set = new HashSet<>(users);
        users.clear();
        users.addAll(set);

        String[] arrOfStr;
        List<UserMissionDto> userMissionDtos = new ArrayList<>();
        List<UserMission> userMissions = new ArrayList<>();
        List<String> dateDisponib = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < users.get(i).getUserMissions().size(); j++) {

                userMissions.add(users.get(i).getUserMissions().get(j));

                dateDisponib = Arrays.asList(users.get(i).getUserMissions().get(j).getDateDisponibiliteList().split("/", -1));

                UserMissionDto userMissionDto=new UserMissionDto();
                userMissionDto.setDateListDispon(dateDisponib);
                userMissionDto.setUserMissions(userMissions);

                userMissionDtos.add(userMissionDto);


            }

        }
        System.out.println("arrOfStr::::" + userMissionDtos.size());

        return userMissions;
    }

    @Override
    public List<UserMissionDto> getListMissionUserDto() {
        List<User> users = userDao.getAllBenevolesDao();
        //    System.out.println("users with duplicate  "+ users);
        //eliminer les doublons
        Set<User> set = new HashSet<>(users);
        users.clear();
        users.addAll(set);

        List<UserMissionDto> userMissionDtos = new ArrayList<>();
        List<UserMission> userMissions = new ArrayList<>();
        List<String> dateDisponib = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < users.get(i).getUserMissions().size(); j++) {

                userMissions.add(users.get(i).getUserMissions().get(j));

                dateDisponib = Arrays.asList(users.get(i).getUserMissions().get(j).getDateDisponibiliteList().split("/", -1));

                UserMissionDto userMissionDto=new UserMissionDto();
                userMissionDto.setDateListDispon(dateDisponib);
                userMissionDto.setUserMissions(userMissions);

                userMissionDtos.add(userMissionDto);


            }

        }
        System.out.println("arrOfStr::::" + userMissionDtos.size());

        return userMissionDtos;
    }

    /***methode get allParicipation By user****/
    @Override
    public List<UserMission> getListMissionUserRest(String username) throws IOException {

        User user = userDao.findByUsernameDao(username);
        List<UserMission> userMissions = user.getUserMissions();
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString2;
        List<UserMission> userMissions1 = new ArrayList<>();
        for (int i = 0; i < userMissions.size(); i++) {
            System.out.println("userMissions" + userMissions.get(i).getMission().getTitre());

            UserMission userMission = mapper.readValue(mapper.writeValueAsString(userMissions.get(i)), UserMission.class);
            jsonInString2 = mapper.writeValueAsString(userMissions.get(i).getMission());

            Mission mission = mapper.readValue(jsonInString2, Mission.class);
            userMission.setMission(mission);
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


    /**************** ConvertListBienToJson ***********/
    public List<Mission> ConvertLisMissionToJson(List<Mission> missions) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        List<Mission> missions1 = new ArrayList<>();
        String jsonInString;

        for (int i = 0; i < missions.size(); i++) {
            jsonInString = mapper.writeValueAsString(missions.get(i));
            Mission mission = mapper.readValue(jsonInString, Mission.class);

            System.out.println(mapper.writeValueAsString(missions.get(i).getId()));
            missions1.add(mission);
        }
        return missions1;
    }
}
