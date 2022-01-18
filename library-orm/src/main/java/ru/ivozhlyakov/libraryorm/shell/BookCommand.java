package ru.ivozhlyakov.libraryorm.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.ivozhlyakov.libraryorm.models.Author;
import ru.ivozhlyakov.libraryorm.models.Book;
import ru.ivozhlyakov.libraryorm.models.Genre;
import ru.ivozhlyakov.libraryorm.service.BookServiceImpl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class BookCommand {

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @ShellMethod(value = "Book list", key = {"book-list", "books", "b"})
    public String bookList() {
        return bookServiceImpl.showTable();
    }

    @ShellMethod(value = "Add book", key = {"add-book", "ab"})
    public void addBook(String name, String author, String genre) {
        bookServiceImpl.save(
                new Book(name
                        , Collections.singletonList(new Author(author))
                        , Collections.singletonList(new Genre(genre))
                        )
        );
    }

    @ShellMethod(value = "Update book", key = {"update-book", "ub"})
    public void updateBook(long id, String name, String author, String genre) {
        bookServiceImpl.save(Book.builder()
                .id(id)
                .name(name)
                .authors(Collections.singletonList(new Author(author)))
                .genres(Collections.singletonList(new Genre(genre)))
                .build());
    }

    @ShellMethod(value = "Delete book", key = {"delete-book", "db"})
    public void delete(long id) {
        bookServiceImpl.deleteById(id);
    }

}
