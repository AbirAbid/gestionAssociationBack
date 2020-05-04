package com.proxym.pfe.gestionAssociationBack.cotisations.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CotisationDto {
    @NotBlank(message = " Veuillez remplir ce champs ! ")

    private String cotisationName;
    @NotNull(message = " Veuillez remplir ce champs ! ")

    private long montant;


    @NotNull(message = " Veuillez remplir ce champs ! ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFin;

}
