package com.proxym.pfe.association.gestion_evenements.entities;

import com.proxym.pfe.association.gestion_sponsors.entities.Sponsor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Data
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



}

