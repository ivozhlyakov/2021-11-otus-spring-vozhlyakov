package ru.ivozhlyakov.libraryorm.repositories;

import ru.ivozhlyakov.libraryorm.models.Author;

import java.util.List;

public interface AuthorsRepositoryJpa {
    List<Author> findAll();
}
