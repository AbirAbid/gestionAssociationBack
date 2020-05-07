package com.proxym.pfe.gestionAssociationBack.bookPackage;

import com.proxym.pfe.gestionAssociationBack.bookPackage.manytomany.repositories.BookPublisher;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "publisher")
    private List<BookPublisher> bookPublishers;

    public Publisher() {

    }

    public Publisher(String name) {
        this.name = name;
    }
}
