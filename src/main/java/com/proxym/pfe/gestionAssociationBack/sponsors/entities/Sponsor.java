package com.proxym.pfe.gestionAssociationBack.sponsors.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class Sponsor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id

    private Long idSponsor;

    @NotEmpty(message = "le champs est vide... !!")
    private String titreSponsor;

    @NotEmpty(message = "Le champs est vide... !!")
    @Email(message = "Format email est non valide ... !!")
    private String emailSponsor;
    private String photoSponsor;

    public Sponsor() {
    }

    public Sponsor(String titreSponsor, String emailSponsor) {
        this.titreSponsor = titreSponsor;
        this.emailSponsor = emailSponsor;
    }
}
