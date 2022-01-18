package ru.ivozhlyakov.libraryorm.service;

import ru.ivozhlyakov.libraryorm.models.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}
