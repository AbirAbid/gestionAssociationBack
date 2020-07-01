package com.proxym.pfe.association.gestion_cotisations.entities;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class Cotisation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotBlank(message = " Veuillez remplir ce champs ! ")

    private String cotisationName;

    private long montant;

    @NotNull(message = " Veuillez remplir ce champs ! ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFin;

}
