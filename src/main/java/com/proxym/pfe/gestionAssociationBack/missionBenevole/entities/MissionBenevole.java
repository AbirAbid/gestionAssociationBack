package com.proxym.pfe.gestionAssociationBack.missionBenevole.entities;

import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
public class MissionBenevole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idM;
    private String titre;
    private String description;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Evenement evenement;
    @OneToMany
    private Set<ParticiperMissionBenevole> participerMissionBenevoles = new HashSet<>();

  /*  @OneToMany(mappedBy = "missionBenevole", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserMission> userMissions;*/
}
