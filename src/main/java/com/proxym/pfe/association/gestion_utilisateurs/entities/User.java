package com.proxym.pfe.association.gestion_utilisateurs.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.proxym.pfe.association.gestion_biens.models.entities.UserBien;
import com.proxym.pfe.association.gestion_missions.entities.UserMission;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        })
})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String nom;

    @NotBlank
    @Size(min = 3, max = 50)
    @Email

    private String username;



    @NotBlank
    @Size(min = 6, max = 100)

    private String password;
    @NotBlank

    private String prenom;

    @NotBlank

    private String telephone;

    private String genre;
    private int isAdmin;

    private Date dateNaissance;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserMission> userMissions;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserBien> userBiens;


    @NotBlank

    private String gouvernoratRes;

    @NotBlank

    private String occupation;

    private float tauxEchange;


    public User(String nom, String username, String password, String prenom,
                Date dateNaissance, String telephone, String gouvernoratRes, String occupation,
                String genre,int isAdmin) {
        this.nom = nom;
        this.username = username;
        this.password = password;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.telephone = telephone;
        this.gouvernoratRes = gouvernoratRes;
        this.occupation = occupation;
        this.genre = genre;
        this.isAdmin=isAdmin;


    }

    public User() {
        this.userMissions = new ArrayList<>();
    }

}
