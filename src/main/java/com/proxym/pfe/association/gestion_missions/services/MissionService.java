package com.proxym.pfe.association.gestion_missions.services;

import com.proxym.pfe.association.gestion_missions.dto.ParticiperMissionDto;
import com.proxym.pfe.association.gestion_missions.dto.UserMissionDto;
import com.proxym.pfe.association.gestion_missions.entities.Mission;
import com.proxym.pfe.association.gestion_missions.entities.MissionUserDisplay;
import com.proxym.pfe.association.gestion_missions.entities.UserMission;
import com.proxym.pfe.association.gestion_utilisateurs.entities.User;

import java.io.IOException;
import java.util.List;

public interface MissionService {
    void saveAllMissionService(List<Mission> missions);

    Mission saveMissionService(Mission mission);

    List<Mission> findAllMissionService() throws IOException;

    List<Mission> findAllMissionByEventService(Long id) throws IOException;

    List<Mission> findAllByEvenement_VilleService(String ville);

    Mission findMissionByIdService(Long id);

    void affecterMission(String username, Long id);

    void libererMission(String username, Long id);

    void participerMissionRest(ParticiperMissionDto participerMissionDto, User user);

    List<UserMission> getListMissionUser();
    List<UserMissionDto> getListMissionUserDto();

    List<UserMission> getListMissionUserRest(String username) throws IOException;

    List<UserMission> getUserMissionByEventRest(String username, Long id) throws IOException;

    List<MissionUserDisplay> getListMissionUserDisplayRest(String username, Long id) throws IOException;

    void libererMissionRest(String username, Long id);


}
