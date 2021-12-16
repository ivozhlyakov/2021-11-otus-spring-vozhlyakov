package ru.ivozhlyakov.library;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.ivozhlyakov.library.domain.Author;
import ru.ivozhlyakov.library.domain.Book;
import ru.ivozhlyakov.library.domain.Genre;
import ru.ivozhlyakov.library.service.AuthorService;
import ru.ivozhlyakov.library.service.BookInfoService;
import ru.ivozhlyakov.library.service.BookService;

import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

@ShellComponent
@RequiredArgsConstructor
public class EventsCommands {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private AuthorService authorService;

    @ShellMethod(value = "getBookNameByID <id>", key = {"getBookNameByID"})
    public String getBookNameByID(Long id) {
        return bookService.getBookNameByID(id);
    }

    @ShellMethod(value = "getBookAll", key = "getBookAll")
    public List<Book> getBookAll() {
        return bookService.getAll();
    }

    @ShellMethod(value = "getBookInfoByID <id>", key = {"getBookInfoByID"})
    public String getBookInfoByID(Long id) {
        return bookInfoService.getBookInfoByID(id);
    }

    @ShellMethod(value = "getAllBookWithInfo", key = {"getAllBookWithInfo"})
    public Set<String> getBookInfoByID() {
        return bookInfoService.getAllBookWithInfo();
    }

    @ShellMethod(value = "addBook <BookName>;<AuthorName>;<GenreName>", key = {"addBook"})
    public String addBook(String string) {
        String[] strings = string.split(";");
        if (strings.length < 3) return "ERROR: Not all parameters were passed";
        return bookInfoService.insert(strings[0], strings[1], strings[2]).compareTo(0L) > 0 ? "book added" : "error";
    }

    @ShellMethod(value = "deleteBookByID <id>", key = {"deleteBookByID"})
    public void deleteBookByID(Long id) {
        bookService.deleteByID(id);
    }

    @ShellMethod(value = "getAuthorByName <name>", key = {"getAuthorByName"})
    public void getAuthorByName(String name) {
        authorService.getByName(name);
    }
}

