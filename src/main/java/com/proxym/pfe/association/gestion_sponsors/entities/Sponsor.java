package com.proxym.pfe.association.gestion_sponsors.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Sponsor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id

    private Long idSponsor;
    @NotBlank(message = " Veuillez remplir ce champs ! ")
    private String titreSponsor;

    @NotBlank(message = " Veuillez remplir ce champs ! ")
    @Email(message = "Format email est invalide !")
    private String emailSponsor;
    private String photoSponsor;
    private int affecte;

    public Sponsor() {
    }

    public Sponsor(String titreSponsor, String emailSponsor) {
        this.titreSponsor = titreSponsor;
        this.emailSponsor = emailSponsor;
    }
}
