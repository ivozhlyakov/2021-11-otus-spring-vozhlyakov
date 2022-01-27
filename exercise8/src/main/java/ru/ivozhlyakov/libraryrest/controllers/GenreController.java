package ru.ivozhlyakov.libraryrest.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ivozhlyakov.libraryrest.models.Genre;
import ru.ivozhlyakov.libraryrest.service.GenreServiceImpl;

import java.util.List;

@RestController
public class GenreController {

    private final GenreServiceImpl genreService;

    public GenreController(GenreServiceImpl genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genres")
    public List<Genre> list() {
        return genreService.findAll();
    }

}
