package ru.ivozhlyakov.libraryrest.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ivozhlyakov.libraryrest.models.Author;
import ru.ivozhlyakov.libraryrest.service.AuthorService;
import ru.ivozhlyakov.libraryrest.service.AuthorServiceImpl;

import java.util.List;

@AllArgsConstructor
@RestController
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/authors")
    public List<Author> list() {
        return authorService.findAll();
    }

}
