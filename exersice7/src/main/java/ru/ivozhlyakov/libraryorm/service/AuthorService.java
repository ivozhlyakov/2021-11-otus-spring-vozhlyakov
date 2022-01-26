package ru.ivozhlyakov.libraryorm.service;

import ru.ivozhlyakov.libraryorm.models.Author;
import ru.ivozhlyakov.libraryorm.models.Genre;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
