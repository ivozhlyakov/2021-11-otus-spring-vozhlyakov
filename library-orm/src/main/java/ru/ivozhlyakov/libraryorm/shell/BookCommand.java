package ru.ivozhlyakov.libraryorm.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.ivozhlyakov.libraryorm.models.Author;
import ru.ivozhlyakov.libraryorm.models.Book;
import ru.ivozhlyakov.libraryorm.models.Genre;
import ru.ivozhlyakov.libraryorm.service.BookService;

import java.util.Collections;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookCommand {

    @Autowired
    private BookService bookService;

    @ShellMethod(value = "Book list", key = {"book-list", "books", "b"})
    public String bookList() {
       return bookService.showAll();
    }

    @ShellMethod(value = "Add book", key = {"add-book", "ab"})
    public void addBook(String name, String author, String genre) {
        bookService.save(
                new Book(name
                        , Collections.singletonList(new Author(author))
                        , Collections.singletonList(new Genre(genre))
                        )
        );
    }

    @ShellMethod(value = "Update book", key = {"update-book", "ub"})
    public void updateBook(long id, String name, String author, String genre) {
        bookService.save(Book.builder()
                .id(id)
                .name(name)
                .authors(Collections.singletonList(new Author(author)))
                .genres(Collections.singletonList(new Genre(genre)))
                .build());
    }

    @ShellMethod(value = "Delete book", key = {"delete-book", "db"})
    public void delete(long id) {
        bookService.deleteById(id);
    }

}
