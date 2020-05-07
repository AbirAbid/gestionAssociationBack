package com.proxym.pfe.gestionAssociationBack.bookPackage;

import com.proxym.pfe.gestionAssociationBack.bookPackage.manytomany.repositories.BookPublisher;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookPublisher> bookPublishers = new ArrayList<>();


    public Book() {
    }

    public Book(String name) {
        this.name = name;
        //bookPublishers = new ArrayList<>();
    }
}
