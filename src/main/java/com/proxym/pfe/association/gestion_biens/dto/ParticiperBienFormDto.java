package com.proxym.pfe.association.gestion_biens.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.proxym.pfe.association.gestion_biens.entities.Bien;

import java.util.Date;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")

public class ParticiperBienFormDto {
    private Bien bien;
    private Integer qteDonnee;
    private Date dateParticipation;

    public ParticiperBienFormDto() {
    }

    public Bien getBien() {
        return bien;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }

    public Integer getQteDonnee() {
        return qteDonnee;
    }

    public void setQteDonnee(Integer qteDonnee) {
        this.qteDonnee = qteDonnee;
    }

    public Date getDateParticipation() {
        return dateParticipation;
    }

    public void setDateParticipation(Date dateParticipation) {
        this.dateParticipation = dateParticipation;
    }
}
