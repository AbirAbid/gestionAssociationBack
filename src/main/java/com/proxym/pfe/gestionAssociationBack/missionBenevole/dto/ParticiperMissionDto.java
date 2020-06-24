package com.proxym.pfe.gestionAssociationBack.missionBenevole.dto;

import com.fasterxml.jackson.annotation.*;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.Mission;

import java.util.Date;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")

public class ParticiperMissionDto {


    private Mission mission;
    private Date demandeDate;
    private String dateDisponibiliteList;

    public String getDateDisponibiliteList() {
        return dateDisponibiliteList;
    }

    public void setDateDisponibiliteList(String dateDisponibiliteList) {
        this.dateDisponibiliteList = dateDisponibiliteList;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public Date getDemandeDate() {
        return demandeDate;
    }

    public void setDemandeDate(Date demandeDate) {
        this.demandeDate = demandeDate;
    }

    public ParticiperMissionDto(Mission mission, Date demandeDate) {
        this.mission = mission;
        this.demandeDate = demandeDate;
    }

    public ParticiperMissionDto() {
    }

}
