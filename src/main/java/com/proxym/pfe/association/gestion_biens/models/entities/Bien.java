package com.proxym.pfe.association.gestion_biens.models.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.proxym.pfe.association.gestion_evenements.entities.Evenement;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")

public class Bien {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String titreBien;
    private Integer qte;
    private Integer totaleqteDonnee;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Evenement evenement;
   /* @OneToMany
    private Set<ParticiperBien> participerBiens = new HashSet<>();*/

    @OneToMany(mappedBy = "bien")
    private List<UserBien> userBiens;

}
