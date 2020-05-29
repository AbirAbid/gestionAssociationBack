package com.proxym.pfe.gestionAssociationBack.user.dto;

import com.proxym.pfe.gestionAssociationBack.biens.entities.UserBien;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.UserMission;
import com.proxym.pfe.gestionAssociationBack.user.entities.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class UpdateProfileForm {
    private Long id;
    @NotBlank

    private String nom;

    @NotBlank
    @Size(max = 50)
    @Email

    private String username;



    @NotBlank

    private String prenom;

    @NotBlank
    private String telephone;

    private Date dateNaissance;

    @NotBlank

    private String occupation;

    @NotBlank

    private String GouvernoratRes;

    @NotBlank

    private String genre;




}
