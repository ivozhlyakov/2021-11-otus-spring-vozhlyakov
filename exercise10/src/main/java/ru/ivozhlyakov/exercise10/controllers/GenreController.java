package ru.ivozhlyakov.exercise10.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ivozhlyakov.exercise10.service.GenreServiceImpl;
import ru.ivozhlyakov.exercise10.models.Genre;

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
