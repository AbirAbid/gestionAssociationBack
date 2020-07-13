package com.proxym.pfe.association.gestion_biens.models.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.proxym.pfe.association.gestion_evenements.entities.Evenement;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Entity
//@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")

public class Bien {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String titreBien;
    private Integer qte;
    private Integer totaleqteDonnee;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    //@ManyToOne
    @JoinColumn(name = "evenement_id")
    private Evenement evenement;

    //en cas ou bidirectionnel ManyToMany
 /*   @OneToMany(mappedBy = "bien")
    private List<UserBien> userBiens;*/


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitreBien() {
        return titreBien;
    }

    public void setTitreBien(String titreBien) {
        this.titreBien = titreBien;
    }

    public Integer getQte() {
        return qte;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    public Integer getTotaleqteDonnee() {
        return totaleqteDonnee;
    }

    public void setTotaleqteDonnee(Integer totaleqteDonnee) {
        this.totaleqteDonnee = totaleqteDonnee;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    /*public List<UserBien> getUserBiens() {
        return userBiens;
    }

    public void setUserBiens(List<UserBien> userBiens) {
        this.userBiens = userBiens;
    }
*/
    public Bien() {
    }
}

