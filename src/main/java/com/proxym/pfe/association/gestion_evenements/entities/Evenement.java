package com.proxym.pfe.association.gestion_evenements.entities;

import com.proxym.pfe.association.gestion_biens.models.entities.Bien;
import com.proxym.pfe.association.gestion_sponsors.entities.Sponsor;
import com.proxym.pfe.association.test.Film;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
//@Data
public class Evenement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private int active;
    @NotBlank
    @Column(name = "titre")
    private String titre;
    @NotBlank
    @Column(name = "description")
    private String description;

    @Column(name = "dateDebut")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")

    private Date dateDebut;

    @Column(name = "dateFin")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date dateFin;

    @NotBlank
    @Column(name = "ville")
    private String ville;

    @NotBlank
    @Column(name = "adresse")
    private String adresse;

    @Column(name = "frais")
    private Double frais;
    private String categorie;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "evenemet_sponsor",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_sponsor"))
    private List<Sponsor> sponsors;

    /*@OneToMany (mappedBy="evenement")
    List<Bien> biens;*/

    public Evenement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
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

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Double getFrais() {
        return frais;
    }

    public void setFrais(Double frais) {
        this.frais = frais;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public List<Sponsor> getSponsors() {
        return sponsors;
    }

    public void setSponsors(List<Sponsor> sponsors) {
        this.sponsors = sponsors;
    }

  /*  public List<Bien> getBiens() {
        return biens;
    }

    public void setBiens(List<Bien> biens) {
        this.biens = biens;
    }*/
}

