package com.proxym.pfe.gestionAssociationBack.user.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proxym.pfe.gestionAssociationBack.bookPackage.Book;
import org.hibernate.annotations.NaturalId;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String nom;

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 100)

    private String password;
    @NotBlank

    private String prenom;

    @NotBlank

    private String telephone;

    private String genre;
    private int isAdmin;
    //@NotBlank
    //  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateNaissance;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    //participation
   /* @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "membres_association",
            joinColumns = @JoinColumn(name = "membre_id"),
            inverseJoinColumns = @JoinColumn(name = "association_id"))
    private Set<Association> associations = new HashSet<>();*/


    @NotBlank

    private String gouvernoratRes;

    @NotBlank

    private String occupation;


    public User(String nom, String username, String email, String password, String prenom,
                Date dateNaissance, String telephone, String gouvernoratRes, String occupation,
                String genre) {
        // public Membre(@NotBlank @Size(min = 3, max = 50) String signUpRequestNom, @NotBlank @Size(min = 3, max = 50) String signUpRequestUsername, @NotBlank @Size(max = 60) @Email String signUpRequestEmail, String gouvernoratRes, String nom, String username, String email, Date dateNaissance, String motDePasse) {
        this.nom = nom;
        this.username = username;
        this.email = email;
        this.password = password;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.telephone = telephone;
        this.gouvernoratRes = gouvernoratRes;
        this.occupation = occupation;
        this.genre = genre;

    }

/*
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            targetEntity = Evenement.class)
    @JoinTable(name = "particBenevole",
            joinColumns = @JoinColumn(name = "User_ID", referencedColumnName = "id", updatable = false, nullable = false),
            inverseJoinColumns = @JoinColumn(name = "Evenement_ID", referencedColumnName = "id", updatable = false, nullable = false))
    private Set<Evenement> evenements = new HashSet<>();*/
}
