package com.proxym.pfe.gestionAssociationBack.evenement.entities;

import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.sponsors.entities.Sponsor;
import com.proxym.pfe.gestionAssociationBack.themeEvent.entities.ThemeEvent;
import com.proxym.pfe.gestionAssociationBack.user.entities.Role;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import lombok.Data;
import org.aspectj.lang.annotation.After;
import org.hibernate.annotations.Fetch;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.context.Theme;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Data
public class Evenement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    int active;
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
    private List<Sponsor> sponsors;



/*
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})

    private ThemeEvent themeEvent;
*/


    //Collection des biens
/*
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "evenement", cascade = CascadeType.ALL)
    private List<Bien> biens;
*/

  /*  @ManyToMany
    @JoinTable(name = "donnerBien",
            joinColumns = @JoinColumn(name = "Evenement_ID", referencedColumnName = "id", updatable = false, nullable = false),
            inverseJoinColumns = @JoinColumn(name = "User_ID", referencedColumnName = "id", updatable = false, nullable = false))
    private Set<User> users = new HashSet<>();*/

}

