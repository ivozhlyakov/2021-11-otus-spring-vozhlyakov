package ru.ivozhlyakov.libraryrest.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ivozhlyakov.libraryrest.models.Author;
import ru.ivozhlyakov.libraryrest.models.Book;
import ru.ivozhlyakov.libraryrest.models.Genre;
import ru.ivozhlyakov.libraryrest.service.BookService;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@RestController
public class BookController {

    private BookService bookService;

    @GetMapping("/books")
    public List<Book> bookList() {
        return bookService.findAll();
    }

    @GetMapping("/books/{id}")
    public Book bookById(@PathVariable("id") long  id)
    {
        return bookService.findById(id).orElse(null);
    }

    @PostMapping("/books")
    public void addBook(@RequestParam(name = "name") String name
            ,@RequestParam(name = "author") String author
            ,@RequestParam(name = "genre") String genre) {
        bookService.save(
                new Book(null, name
                        , Collections.singletonList(new Author(null, author))
                        , Collections.singletonList(new Genre(null, genre))
                        )
        );
    }

    @PutMapping("/books/{id}")
    public void updateBook(@PathVariable("id") long id
            ,@RequestParam(name = "name") String name
            ,@RequestParam(name = "author") String author
            ,@RequestParam(name = "genre") String genre) {
        bookService.save(Book.builder()
                .id(id)
                .name(name)
                .authors(Collections.singletonList(new Author(null, author)))
                .genres(Collections.singletonList(new Genre(null, genre)))
                .build());
    }

    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable("id") long id) {
        bookService.deleteById(id);
    }

    @PatchMapping("/books/{id}/name")
    public void updateBookNameById(@PathVariable("id") Long id
            ,@RequestParam(name = "name") String name) {
        bookService.updateNameById(id, name);
    }

}
