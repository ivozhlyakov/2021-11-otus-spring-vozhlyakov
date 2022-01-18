package ru.ivozhlyakov.libraryorm.repositories;

import ru.ivozhlyakov.libraryorm.models.Genre;

import java.util.List;

public interface GenreRepositoryJpa {
    List<Genre> findAll();
}
