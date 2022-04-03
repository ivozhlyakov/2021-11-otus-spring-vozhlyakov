package ru.ivozhlyakov.rest.controll;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.ivozhlyakov.rest.dto.Book;
import ru.ivozhlyakov.rest.service.BookService;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookController {

    private BookService bookService;

    @GetMapping("/books")
    public List<Book> bookList() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book bookById(@PathVariable("id") long  id) {
        return bookService.getBookById(id);
    }

}
