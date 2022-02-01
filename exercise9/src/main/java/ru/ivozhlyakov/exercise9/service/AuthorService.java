package ru.ivozhlyakov.exercise9.service;

import ru.ivozhlyakov.exercise9.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
