package ru.ivozhlyakov.exercise10.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ivozhlyakov.exercise10.models.Author;
import ru.ivozhlyakov.exercise10.models.Book;
import ru.ivozhlyakov.exercise10.service.BookService;
import ru.ivozhlyakov.exercise10.service.BookServiceImpl;
import ru.ivozhlyakov.exercise10.models.Genre;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
public class BookController {

    private BookService bookService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage(HttpServletResponse response) throws IOException {
        response.sendRedirect("books");
        return null;
    }

    @GetMapping("/books")
    public List<Book> bookList() {
        return bookService.findAll();
    }

    @GetMapping("/books/{id}")
    public Book bookById(@PathVariable("id") long  id) {
        return bookService.findById(id).orElse(null);
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestParam(name = "name") String name
            , @RequestParam(name = "author") String author
            , @RequestParam(name = "genre") String genre) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(
                new Book(name
                        , Collections.singletonList(new Author(author))
                        , Collections.singletonList(new Genre(genre))
                )
        ));
    }

    @PutMapping("/books/{id}")
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
