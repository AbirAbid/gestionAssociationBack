package com.proxym.pfe.gestionAssociationBack.biens.dto;

import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.biens.entities.ParticiperBien;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import lombok.Data;

@Data
public class ParticiperBienFormDto {
    private Bien bien;
    private Integer qteDon;
    // private User user;

}