package com.proxym.pfe.gestionAssociationBack.evenement.dto;

import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.MissionBenevole;
import com.proxym.pfe.gestionAssociationBack.sponsors.entities.Sponsor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Data
public class EvenementDto {
    Long id;

    @NotBlank(message = " Veuillez remplir ce champs ! ")
    private String titre;

    @NotBlank(message = " Veuillez remplir ce champs ! ")
    private String description;

    @NotNull(message = " Veuillez remplir ce champs ! ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;

    @NotNull(message = " Veuillez remplir ce champs ! ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFin;

    @NotBlank(message = " Veuillez remplir ce champs ! ")
    private String ville;

    @NotBlank(message = " Veuillez remplir ce champs ! ")
    private String adresse;

    private Double frais;


    private List<Sponsor> sponsors ;


    private List<Bien> biens;
    private List<MissionBenevole> missionBenevoles;

    public EvenementDto(List<Bien> biens, List<MissionBenevole> missionBenevoles) {
        this.biens = biens;
        this.missionBenevoles = missionBenevoles;
    }

    public EvenementDto() {
        this.biens = new ArrayList<>();
        this.missionBenevoles = new ArrayList<>();
    }

    public void addBien(Bien bien) {
        this.biens.add(bien);
    }


    public void addMissionBenevole(MissionBenevole missionBenevole) {
        this.missionBenevoles.add(missionBenevole);
    }

    public void affectToEventDto(Evenement e) {
        id = e.getId();
        titre = e.getTitre();
        dateDebut = e.getDateDebut();
        dateFin = e.getDateFin();
        description = e.getDescription();
        ville = e.getVille();
        adresse = e.getAdresse();
        frais = e.getFrais();
        sponsors = e.getSponsors();
    }

}
