package com.proxym.pfe.gestionAssociationBack.bookPackage;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    void saveAll(List<Book> books);
    //  void editAll(List<Book> books);
}
