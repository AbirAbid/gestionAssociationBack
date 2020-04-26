package com.proxym.pfe.gestionAssociationBack.bookPackage.Etudiant;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "champs est vide")
    @Size(min = 4, max = 10, message = "error")
    private String nom;
    @NotEmpty
    @Email
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;

    private String photo;

    public Etudiant() {
    }

    public Etudiant(String nom, String email, Date dateNaissance, String photo) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.photo = photo;
    }

}
