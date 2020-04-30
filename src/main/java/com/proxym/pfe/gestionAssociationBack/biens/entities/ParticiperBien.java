package com.proxym.pfe.gestionAssociationBack.biens.entities;

import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ParticiperBien {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idP;
    @Column(name = "qteDonnee")
    private int qteDonnee;
    @ManyToOne
    private User user;
}
