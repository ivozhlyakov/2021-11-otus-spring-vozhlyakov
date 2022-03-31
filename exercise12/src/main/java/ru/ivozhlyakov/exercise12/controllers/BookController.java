package ru.ivozhlyakov.exercise12.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ivozhlyakov.exercise12.domain.Author;
import ru.ivozhlyakov.exercise12.domain.Book;
import ru.ivozhlyakov.exercise12.domain.Genre;
import ru.ivozhlyakov.exercise12.service.BookService;
import ru.ivozhlyakov.exercise12.service.BookServiceImpl;


import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
public class BookController {

    private BookService bookService;

    @GetMapping("/api/books")
    public List<Book> bookList() {
        return bookService.findAll();
    }

    @GetMapping("/api/books/{id}")
    public Book bookById(@PathVariable("id") long  id) {
        return bookService.findById(id).orElse(null);
    }

    @PostMapping("/api/books")
    public void addBook(@RequestParam(name = "name") String name
            ,@RequestParam(name = "author") String author
            ,@RequestParam(name = "genre") String genre) {
        bookService.save(
                new Book(name
                        , Collections.singletonList(new Author(author))
                        , Collections.singletonList(new Genre(genre))
                        )
        );
    }

    @PutMapping("/api/books/{id}")
    public void updateBook(@PathVariable("id") long id
            ,@RequestParam(name = "name") String name
            ,@RequestParam(name = "author") String author
            ,@RequestParam(name = "genre") String genre) {
        bookService.save(Book.builder()
                .id(id)
                .name(name)
                .authors(Collections.singletonList(new Author(author)))
                .genres(Collections.singletonList(new Genre(genre)))
                .build());
    }

    @DeleteMapping("/api/books/{id}")
    public void delete(@PathVariable("id") long id) {
        bookService.deleteById(id);
    }

    @PatchMapping("/api/books/{id}/name")
    public void updateBookNameById(@PathVariable("id") Long id
            ,@RequestParam(name = "name") String name) {
        bookService.updateNameById(id, name);
    }

}
