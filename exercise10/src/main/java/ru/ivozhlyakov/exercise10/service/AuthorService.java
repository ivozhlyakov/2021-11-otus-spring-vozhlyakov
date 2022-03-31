package ru.ivozhlyakov.exercise10.service;

import ru.ivozhlyakov.exercise10.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
