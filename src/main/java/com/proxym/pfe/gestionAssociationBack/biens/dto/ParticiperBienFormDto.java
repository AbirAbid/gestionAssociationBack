package com.proxym.pfe.gestionAssociationBack.biens.dto;

import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.biens.entities.ParticiperBien;
import lombok.Data;

@Data
public class ParticiperBienFormDto {
    private Bien bien;
    private ParticiperBien participerBien;
}
