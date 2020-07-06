package com.proxym.pfe.association.gestion_missions.dto;

import com.proxym.pfe.association.gestion_missions.entities.Mission;
import lombok.Data;

@Data
public class MissionUserDisplay {
    private Mission mission;
    private int exist;
    private int affected;
}
