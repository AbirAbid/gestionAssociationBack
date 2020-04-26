package com.proxym.pfe.gestionAssociationBack.biens.entities;

import com.proxym.pfe.gestionAssociationBack.bookPackage.Book;
import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Bien {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String titreBien;
    private int qte;

    @ManyToOne(cascade = CascadeType.ALL)
    private Evenement evenement;


}
