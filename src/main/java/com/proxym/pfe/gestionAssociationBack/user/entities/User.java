package com.proxym.pfe.gestionAssociationBack.user.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.proxym.pfe.gestionAssociationBack.biens.entities.UserBien;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.UserMission;
import org.hibernate.annotations.NaturalId;

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
        })/*,
        @UniqueConstraint(columnNames = {
                "email"
        })*/
})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")

public class User {
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

  /*  @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;*/

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


    public User(String nom, String username,  String password, String prenom,
                Date dateNaissance, String telephone, String gouvernoratRes, String occupation,
                String genre) {
        this.nom = nom;
        this.username = username;
     //   this.email = email;
        this.password = password;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.telephone = telephone;
        this.gouvernoratRes = gouvernoratRes;
        this.occupation = occupation;
        this.genre = genre;

    }

    public User() {
        this.userMissions = new ArrayList<>();
    }

}
