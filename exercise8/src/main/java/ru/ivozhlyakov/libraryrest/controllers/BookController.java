package ru.ivozhlyakov.libraryrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ivozhlyakov.libraryrest.models.Author;
import ru.ivozhlyakov.libraryrest.models.Book;
import ru.ivozhlyakov.libraryrest.models.Genre;
import ru.ivozhlyakov.libraryrest.service.BookServiceImpl;

import java.util.Collections;
import java.util.List;

@RestController
public class BookController {

    private BookServiceImpl bookServiceImpl;

    public BookController(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    @GetMapping("/books")
    public List<Book> bookList() {
        return bookServiceImpl.findAll();
    }

    @GetMapping("/books/{id}")
    public Book bookById(@PathVariable("id") long  id) {
        return bookServiceImpl.findById(id).orElse(null);
    }

    @PostMapping("/books")
    public void addBook(@RequestParam(name = "name") String name
            ,@RequestParam(name = "author") String author
            ,@RequestParam(name = "genre") String genre) {
        bookServiceImpl.save(
                new Book(name
                        , Collections.singletonList(new Author(author))
                        , Collections.singletonList(new Genre(genre))
                        )
        );
    }

    @PutMapping("/books/{id}")
    public void updateBook(@PathVariable("id") long id
            ,@RequestParam(name = "name") String name
            ,@RequestParam(name = "author") String author
            ,@RequestParam(name = "genre") String genre) {
        bookServiceImpl.save(Book.builder()
                .id(id)
                .name(name)
                .authors(Collections.singletonList(new Author(author)))
                .genres(Collections.singletonList(new Genre(genre)))
                .build());
    }

    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable("id") long id) {
        bookServiceImpl.deleteById(id);
    }

    @PatchMapping("/books/{id}/name")
    public void updateBookNameById(@PathVariable("id") Long id
            ,@RequestParam(name = "name") String name) {
        bookServiceImpl.updateNameById(id, name);
    }

}
