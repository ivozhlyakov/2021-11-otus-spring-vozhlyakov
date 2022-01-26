package ru.ivozhlyakov.libraryorm.shell;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.ivozhlyakov.libraryorm.models.Author;
import ru.ivozhlyakov.libraryorm.models.Genre;
import ru.ivozhlyakov.libraryorm.service.AuthorServiceImpl;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCommand {

    private final AuthorServiceImpl authorService;

    @ShellMethod(value = "Author list", key = {"author-list", "authors", "a"})
    public List<Author> list() {
        return authorService.findAll();
    }

}
