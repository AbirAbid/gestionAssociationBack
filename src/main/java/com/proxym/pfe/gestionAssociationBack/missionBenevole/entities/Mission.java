package com.proxym.pfe.gestionAssociationBack.missionBenevole.entities;

import com.fasterxml.jackson.annotation.*;
import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import lombok.Data;

import javax.persistence.*;

import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    //private int enAtte;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Evenement evenement;

    @OneToMany(mappedBy = "mission")
    private List<UserMission> userMissions;

    public Mission() {
    }

    public Mission(String s, String s1) {
        titre = s;
        description = s1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UserMission> getUserMissions() {
        return userMissions;
    }

    public void setUserMissions(List<UserMission> userMissions) {
        this.userMissions = userMissions;
    }

    public Mission(Long id, List<UserMission> userMissions) {
        this.id = id;
        this.userMissions = userMissions;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
}
