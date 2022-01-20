package ru.ivozhlyakov.library.service;

import ru.ivozhlyakov.library.domain.Author;

public interface AuthorService {
    Long insert(Author author);
    Author getById(Long id);
}
