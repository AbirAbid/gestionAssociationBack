package com.proxym.pfe.gestionAssociationBack.missionBenevole.entities;

import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class MissionBenevole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idM;
    private String titre;
    private String description;
    // @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})

    private Evenement evenement;

   /* @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinTable(
            name = "particBenevole",
            joinColumns = @JoinColumn(name = "MissionBen_ID", referencedColumnName = "id", updatable = false, nullable = false),
            inverseJoinColumns = @JoinColumn(name = "User_ID", referencedColumnName = "id", updatable = false, nullable = false)
    )
    @MapKeyJoinColumn(name = "Evenement_Id")
    private Map<Evenement, User> evenementUserHashMap = new HashMap<>();*/

}
