package com.proxym.pfe.gestionAssociationBack.missionBenevole.dto;

import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.UserMission;
import lombok.Data;

import java.util.List;

@Data
public class UserMissionDto {
    List<UserMission> userMissions;
    List<String> dateListDispon;
}
