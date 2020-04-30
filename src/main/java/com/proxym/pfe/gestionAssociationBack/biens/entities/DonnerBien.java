package com.proxym.pfe.gestionAssociationBack.biens.entities;

import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class DonnerBien {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "qteDonnee")
    private Long qteDonnee;
    @ManyToOne
    private User user;
}
