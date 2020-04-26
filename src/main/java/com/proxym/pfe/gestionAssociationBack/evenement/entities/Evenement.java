package com.proxym.pfe.gestionAssociationBack.evenement.entities;

import com.proxym.pfe.gestionAssociationBack.sponsors.entities.Sponsor;
import lombok.Data;
import org.aspectj.lang.annotation.After;
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
    @NotBlank
    @Column(name = "titre")
    private String titre;
    @NotBlank
    @Column(name = "description")
    private String description;

    @Column(name = "dateDebut")
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date dateDebut;

    @Column(name = "dateFin")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFin;

    @NotBlank
    @Column(name = "ville")
    private String ville;

    @NotBlank
    @Column(name = "adresse")
    private String adresse;

    @Column(name = "frais")
    private Double frais;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "evenemet_sponsor",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_sponsor"))
    private Set<Sponsor> sponsors = new HashSet<>();

 /*   @ManyToMany
    @JoinTable(name = "particBenevole",
            joinColumns = @JoinColumn(name = "Evenement_ID", referencedColumnName = "id", updatable = false, nullable = false),
            inverseJoinColumns = @JoinColumn(name = "User_ID", referencedColumnName = "id", updatable = false, nullable = false))
    private Set<User> users = new HashSet<>();*/

    //Collection des biens
    /*@OneToMany
            (fetch = FetchType.EAGER, mappedBy = "evenement", cascade = CascadeType.ALL)
    @Fetch(value= FetchMode.SELECT)
    private Set<Bien> biens;
*/

}

