package com.proxym.pfe.association.test;

import lombok.Data;

import javax.persistence.*;


@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String titre;
   @ManyToOne
   @JoinColumn (name="id_realisateur")
   Realisateur realisateur;

    public Film(String t) {
        this.titre = t;
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

    public Realisateur getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(Realisateur realisateur) {
        this.realisateur = realisateur;
    }
}
