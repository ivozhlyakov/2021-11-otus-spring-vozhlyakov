package ru.ivozhlyakov.exercise10.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ivozhlyakov.exercise10.service.GenreService;
import ru.ivozhlyakov.exercise10.service.GenreServiceImpl;
import ru.ivozhlyakov.exercise10.models.Genre;

import java.util.List;

@RestController
@AllArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/genres")
    public List<Genre> list() {
        return genreService.findAll();
    }

}
