package com.proxym.pfe.gestionAssociationBack.bookPackage.manytomany.repositories;

import com.proxym.pfe.gestionAssociationBack.bookPackage.Book;
import com.proxym.pfe.gestionAssociationBack.bookPackage.Publisher;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "book_publisher")
public class BookPublisher implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Id
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @Column(name = "published_date")
    private Date publishedDate;

    private int affected;

}
