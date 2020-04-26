package com.proxym.pfe.gestionAssociationBack.bookPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/books")
public class MultipleBooksController {

    @Autowired
    private BookService bookService;

    @Autowired
    BookRepositories bookRepositories;

    @GetMapping(value = "/all")
    public String showAll(Model model) {
        model.addAttribute("books", bookService.findAll());

        return "allBooks";
    }

    @GetMapping(value = "/create")
    public String showCreateForm(Model model) {
        BooksCreationDto booksForm = new BooksCreationDto();

        //  for (int i = 1; i <= 3; i++) {
        booksForm.addBook(new Book());
        // }

        model.addAttribute("form", booksForm);

        return "createBooksForm";
    }


    @PostMapping(value = "/save")
    public String saveBooks(@ModelAttribute BooksCreationDto form, Model model) {
        bookService.saveAll(form.getBooks());
        model.addAttribute("books", bookService.findAll());
        return "redirect:/books/all";
    }
/*
    @GetMapping(value = "/edit")
    public String showEditForm(Model model) {
        List<Book> books = new ArrayList<>();
        bookService.findAll()
                .iterator().forEachRemaining(books::add);
        System.out.println(" bookService.findAll()");
        //
        //  .forEachRemaining(books::add);
        model.addAttribute("form", new BooksCreationDto(books));
        return "editBooksForm";
    }

    @PutMapping(value = "/update")
    public String updateBooks(@ModelAttribute BooksCreationDto form, Model model,List<Book> books) {
        List<Book> bookMap = books.stream().collect(Collectors.toList());
        System.out.println(bookMap);
        System.out.println();
        bookRepositories.flush();
        model.addAttribute("books", bookService.findAll());
        return "redirect:/books/all";
    }*/
}
