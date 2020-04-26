package com.proxym.pfe.gestionAssociationBack.cotisations.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;
@Data
public class CotisationDto {
    @NotBlank
    private String cotisationName;
    private long montant;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFin;

}
