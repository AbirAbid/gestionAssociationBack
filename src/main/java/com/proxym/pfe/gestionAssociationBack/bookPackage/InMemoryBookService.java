package com.proxym.pfe.gestionAssociationBack.bookPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class InMemoryBookService implements BookService {
    @Autowired
    BookRepositories bookRepositories;
    //static List<Book> bookList;
   // static Map<Long, Book> booksDB = new HashMap<>();

    @Override
    public List<Book> findAll() {
       // bookList = bookRepositories.findAll();
        return bookRepositories.findAll();
        //return new ArrayList<>(booksDB.values());
    }

    @Override
    public void saveAll(List<Book> books) {
        bookRepositories.saveAll(books.stream().collect(Collectors.toList()));

     /*   long nextId = getNextId();
        for (Book book : books) {
            if (book.getId() == 0) {
                book.setId(nextId++);
            }
        }*/
        //Map<Long, Book> bookMap = books.stream().collect(Collectors.toMap(Book::getId, Function.identity()));

      /*  List<Book> bookMap = books.stream().collect(Collectors.toList());
        System.out.println(bookMap);*/
       // bookList.addAll(bookMap);
        // booksDB.putAll(bookMap);


    }

  /*  @Override
    public void editAll(List<Book> books) {
        List<Book> bookMap = books.stream().collect(Collectors.toList());
        System.out.println(bookMap);
        bookList.addAll(bookMap);
        bookRepositories.flush();


    }
*/
  /*  private Long getNextId() {
        return booksDB.keySet()
                .stream()
                .mapToLong(value -> value)
                .max()
                .orElse(0) + 1;
        //le cas d'erreur en renvoyant 0
    }*/
}
