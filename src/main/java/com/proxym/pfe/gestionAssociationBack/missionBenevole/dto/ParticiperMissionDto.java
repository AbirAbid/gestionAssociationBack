package com.proxym.pfe.gestionAssociationBack.missionBenevole.dto;

import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.MissionBenevole;
import lombok.Data;

import java.util.Date;

@Data
public class ParticiperMissionDto {

    private MissionBenevole missionBenevole;

    private Date dateD;
}
