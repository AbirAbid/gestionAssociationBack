package com.proxym.pfe.association.gestion_evenements.dto;

import com.proxym.pfe.association.gestion_evenements.entities.Evenement;
import com.proxym.pfe.association.gestion_biens.entities.Bien;
import com.proxym.pfe.association.gestion_missions.entities.Mission;
import com.proxym.pfe.association.gestion_sponsors.entities.Sponsor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Data
public class EvenementDto {
    Long id;
    private int active;

    @NotBlank(message = " Veuillez remplir ce champs ! ")
    private String titre;

    @NotBlank(message = " Veuillez remplir ce champs ! ")
    private String description;

    @NotNull(message = " Veuillez remplir ce champs ! ")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date dateDebut;

    @NotNull(message = " Veuillez remplir ce champs ! ")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date dateFin;

    @NotBlank(message = " Veuillez remplir ce champs ! ")
    private String ville;

    @NotBlank(message = " Veuillez remplir ce champs ! ")
    private String adresse;

    private Double frais;
    private String categorie;


    private List<Sponsor> sponsors;

    private List<Bien> biens;
    //   private List<MissionBenevole> missionBenevoles;
    private List<Mission> missions;

    public EvenementDto(List<Bien> biens, List<Mission> missions) {
        this.biens = biens;
        //this.missionBenevoles = missionBenevoles;
        this.missions = missions;
    }

    public EvenementDto() {
        this.biens = new ArrayList<>();
        // this.missionBenevoles = new ArrayList<>();
        this.missions = new ArrayList<>();
    }

    public void addBien(Bien bien) {
        this.biens.add(bien);
    }


    /*public void addMissionBenevole(MissionBenevole missionBenevole) {
        this.missionBenevoles.add(missionBenevole);
    }*/
    public void addMission(Mission mission) {
        this.missions.add(mission);
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
        active = e.getActive();
        categorie = e.getCategorie();
    }

}
