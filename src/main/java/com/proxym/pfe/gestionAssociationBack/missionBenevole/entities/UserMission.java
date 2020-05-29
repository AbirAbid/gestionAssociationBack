package com.proxym.pfe.gestionAssociationBack.missionBenevole.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class UserMission implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "mission_id")
    private Mission mission;

    private Date dateDiponibilite;

    private Date demandeDate;
    private int affected;
    private int enAttente;

    public User getUser() {
        return user;
    }

    public Mission getMission() {
        return mission;
    }

    public Date getDemandeDate() {
        return demandeDate;
    }

    public int getAffected() {
        return affected;
    }

    public int getEnAttente() {
        return enAttente;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public void setDemandeDate(Date demandeDate) {
        this.demandeDate = demandeDate;
    }

    public void setAffected(int affected) {
        this.affected = affected;
    }

    public void setEnAttente(int enAttente) {
        this.enAttente = enAttente;
    }

    public UserMission() {
    }
}
