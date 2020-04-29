package com.proxym.pfe.gestionAssociationBack.biens.entities;

import com.proxym.pfe.gestionAssociationBack.bookPackage.Book;
import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Bien {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String titreBien;
    private int qte;

    //@ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Evenement evenement;

    @ManyToMany
    @JoinTable(name = "donnerBien",
            joinColumns = @JoinColumn(name = "Bien_ID", referencedColumnName = "id", updatable = false, nullable = false),
            inverseJoinColumns = @JoinColumn(name = "User_ID", referencedColumnName = "id", updatable = false, nullable = false))
    private Set<User> users = new HashSet<>();

}
