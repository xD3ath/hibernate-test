package com.developia.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/create")
    public void create(@ModelAttribute Book book) {
        bookRepository.save(book);

    }

    @GetMapping("/books")
    public String getBooks(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "books";

    }


    @GetMapping("/view")
    public String view(Model model, @RequestParam Long id) {
        System.out.println("ID: " + id);
        try {
            Book book = bookRepository.findById(id).get();
            model.addAttribute("book", book);
            return "view";
        } catch (Exception ex) {
            return "not_found";
        }
    }

    @GetMapping("/update")
    public String updateBook(Book book, @RequestParam Long id) {
        try {
            Book update = bookRepository.findById(id).get();
            bookRepository.save(book);
            return "update";
        } catch (Exception ex) {
            return "not_found";
        }
    }

    @GetMapping("/delete")
    public String deleteBook(Book book, @RequestParam Long id) {
        try {
            Book delete = bookRepository.findById(id).get();
            bookRepository.delete(book);
            return "delete";
        } catch (Exception ex) {
            return "not_found";
        }
    }

}
