package ru.ivozhlyakov.libraryrest.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ivozhlyakov.libraryrest.models.Genre;
import ru.ivozhlyakov.libraryrest.service.GenreService;
import ru.ivozhlyakov.libraryrest.service.GenreServiceImpl;

import java.util.List;

@AllArgsConstructor
@RestController
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/genres")
    public List<Genre> list() {
        return genreService.findAll();
    }

}
