package ru.ivozhlyakov.library.service;

import ru.ivozhlyakov.library.domain.Genre;

public interface GenreService {
    Long insert(Genre genre);
    Genre getByName(Long id);
}
