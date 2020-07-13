package com.proxym.pfe.association.test;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Realisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String nom;
    @OneToMany (mappedBy="realisateur")
    Set<Film> films;

    public Realisateur(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }
}
