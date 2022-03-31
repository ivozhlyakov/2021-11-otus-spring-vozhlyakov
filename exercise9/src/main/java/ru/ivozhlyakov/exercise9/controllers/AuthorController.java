package ru.ivozhlyakov.exercise9.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ivozhlyakov.exercise9.models.Author;
import ru.ivozhlyakov.exercise9.service.AuthorService;
import ru.ivozhlyakov.exercise9.service.AuthorServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/authors")
    public List<Author> list() {
        return authorService.findAll();
    }

}
