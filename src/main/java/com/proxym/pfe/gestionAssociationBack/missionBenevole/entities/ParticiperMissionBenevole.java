package com.proxym.pfe.gestionAssociationBack.missionBenevole.entities;

import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "participer_mission")
public class ParticiperMissionBenevole {
    @Id
    @GeneratedValue

    private Long idPm;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)

    private User user;

    @ManyToOne
    @JoinColumn(name = "missionBenevole_id", nullable = false)
    private MissionBenevole missionBenevole;

    private Date dateDemande;

    private int affected;
}
