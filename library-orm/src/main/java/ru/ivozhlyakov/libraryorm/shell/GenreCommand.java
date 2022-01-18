package ru.ivozhlyakov.libraryorm.shell;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.ivozhlyakov.libraryorm.models.Comment;
import ru.ivozhlyakov.libraryorm.models.Genre;
import ru.ivozhlyakov.libraryorm.service.GenreServiceImpl;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class GenreCommand {

    private final GenreServiceImpl genreService;

    @ShellMethod(value = "Genre list", key = {"genre-list", "genres", "g"})
    public List<Genre> list() {
        return genreService.findAll();
    }

}
