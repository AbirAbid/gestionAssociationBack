package com.proxym.pfe.gestionAssociationBack.missionBenevole.dto;

import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.MissionBenevole;

import java.util.ArrayList;
import java.util.List;

public class MissionBenDto {
    private List<MissionBenevole> missionBenevoles;

    public MissionBenDto(List<MissionBenevole> missionBenevoles) {
        this.missionBenevoles = missionBenevoles;
    }

    public MissionBenDto() {
        this.missionBenevoles = new ArrayList<>();
    }

    public List<MissionBenevole> getMissionBenevoles() {
        return missionBenevoles;
    }

    public void setMissionBenevoles(List<MissionBenevole> missionBenevoles) {
        this.missionBenevoles = missionBenevoles;
    }

    public void addMissionBenevole(MissionBenevole missionBenevole) {
        this.missionBenevoles.add(missionBenevole);
    }

}
