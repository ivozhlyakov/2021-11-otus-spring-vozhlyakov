package ru.ivozhlyakov.exercise9.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ivozhlyakov.exercise9.models.Author;
import ru.ivozhlyakov.exercise9.service.AuthorServiceImpl;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorServiceImpl authorService;

    public AuthorController(AuthorServiceImpl authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public List<Author> list() {
        return authorService.findAll();
    }

}
