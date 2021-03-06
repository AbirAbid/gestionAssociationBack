package com.proxym.pfe.association.gestion_utilisateurs.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Data
public class SignUpForm {
    @NotBlank

    private String nom;

    @NotBlank
    @Size( max = 50)
    @Email

    private String username;

    private Set<String> role;

    @NotBlank
    @Size(min = 2, max = 40)
    private String password;

    @NotBlank

    private String prenom;

    @NotBlank
    private String telephone;

   // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateNaissance;

    @NotBlank

    private String occupation;

    @NotBlank

    private String GouvernoratRes;

    @NotBlank

    private String genre;
    private int isAdmin;
}
