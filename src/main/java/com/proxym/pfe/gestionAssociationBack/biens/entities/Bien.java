package com.proxym.pfe.gestionAssociationBack.biens.entities;

import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import lombok.Data;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "bien")

@Data

public class Bien {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id

    private Long id;

    private String titreBien;
    private Integer qte;
    private Integer totaleqteDonnee;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Evenement evenement;
    @OneToMany

    private Set<ParticiperBien> participerBiens = new HashSet<>();

}
