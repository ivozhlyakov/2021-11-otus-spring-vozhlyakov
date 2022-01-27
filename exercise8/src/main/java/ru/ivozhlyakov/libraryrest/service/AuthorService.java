package ru.ivozhlyakov.libraryrest.service;

import ru.ivozhlyakov.libraryrest.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
