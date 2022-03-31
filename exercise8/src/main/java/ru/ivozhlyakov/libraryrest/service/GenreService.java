package ru.ivozhlyakov.libraryrest.service;

import ru.ivozhlyakov.libraryrest.models.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}
