package ru.ivozhlyakov.library;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.ivozhlyakov.library.domain.Author;
import ru.ivozhlyakov.library.domain.Book;
import ru.ivozhlyakov.library.domain.Genre;
import ru.ivozhlyakov.library.service.AuthorServiceImpl;
import ru.ivozhlyakov.library.service.BookServiceImpl;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class EventsCommands {

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @Autowired
    private AuthorServiceImpl authorServiceImpl;

    private Book createBook(String name, String authorFio, String genreName) {
        return Book.builder()
                .name(name)
                .author(Author.builder()
                        .brief(authorFio)
                        .build())
                .genre(Genre.builder()
                        .name(genreName)
                        .build())
                .build();
    }

    @ShellMethod(value = "getBookByID <id>", key = {"getBookByID"})
    public Book getBookNameByID(Long id) {
        return bookServiceImpl.getById(id);
    }

    @ShellMethod(value = "getBookAll", key = "getBookAll")
    public List<Book> getBookAll() {
        return bookServiceImpl.getAll();
    }

    @ShellMethod(value = "addBook <BookName> <AuthorName> <GenreName>", key = {"addBook", "ab"})
    public String addBook(String bookName, String authorName, String genreName) {
        Book book = createBook(bookName, authorName, genreName);
        return bookServiceImpl.insert(book).compareTo(0L) > 0 ? "book added" : "error";
    }

    @ShellMethod(value = "addBook <BookName> <Author_ID> <Genre_ID>", key = {"addBookWithID", "abId"})
    public String addBook2(String bookName, Long author_id, Long genre_id) {
        Book book = Book.builder()
                .name(bookName)
                .author(Author.builder().id(author_id).build())
                .genre(Genre.builder().id(genre_id).build())
                .build();
        return bookServiceImpl.insert(book).compareTo(0L) > 0 ? "book added" : "error";
    }

    @ShellMethod(value = "deleteBookByID <id>", key = {"deleteBookByID"})
    public void deleteBookByID(Long id) {
        bookServiceImpl.deleteByID(id);
    }

    @ShellMethod(value = "updateBookNameByID <id> <bookName>", key = {"updateBookNameByID"})
    public void updateBookNameByID(Long id, String name){
        bookServiceImpl.updateBookNameByID(
                Book.builder()
                .id(id)
                .name(name)
                .build()
        );
    }
}

