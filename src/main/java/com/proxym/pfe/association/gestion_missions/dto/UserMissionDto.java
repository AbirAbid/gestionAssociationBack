package com.proxym.pfe.association.gestion_missions.dto;

import com.proxym.pfe.association.gestion_missions.entities.UserMission;
import lombok.Data;

import java.util.List;

@Data
public class UserMissionDto {
    List<UserMission> userMissions;
    List<String> dateListDispon;
}
