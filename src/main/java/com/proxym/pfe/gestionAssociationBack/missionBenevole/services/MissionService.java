package com.proxym.pfe.gestionAssociationBack.missionBenevole.services;

import com.proxym.pfe.gestionAssociationBack.missionBenevole.dto.ParticiperMissionDto;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.Mission;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.MissionUserDisplay;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.UserMission;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;

public interface MissionService {
    void saveAllMissionService(List<Mission> missions);

    Mission saveMissionService(Mission mission);

    List<Mission> findAllMissionService();

    List<Mission> findAllMissionByEventService(Long id);

    List<Mission> findAllByEvenement_VilleService(String ville);

    Mission findMissionByIdService(Long id);

    void affecterMission(User user, Mission mission);

    void libererMission(User user, Mission mission);

    void participerMissionRest(ParticiperMissionDto participerMissionDto, User user);

    List<UserMission> getListMissionUserRest(String username) throws IOException;

    List<UserMission> getUserMissionByEventRest(String username, Long id) throws IOException;

    List<MissionUserDisplay> getListMissionUserDisplayRest(String username, Long id) throws IOException;

    void libererMissionRest(String username, Long id);



}
