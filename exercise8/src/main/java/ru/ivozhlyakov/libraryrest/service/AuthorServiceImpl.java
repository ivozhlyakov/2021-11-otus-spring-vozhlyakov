package ru.ivozhlyakov.libraryrest.service;

import org.springframework.stereotype.Service;
import ru.ivozhlyakov.libraryrest.models.Author;
import ru.ivozhlyakov.libraryrest.repositories.AuthorsRepositoryJpa;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService{

    private AuthorsRepositoryJpa repository;

    public AuthorServiceImpl(AuthorsRepositoryJpa repository) {
        this.repository = repository;
    }

    @Override
    public List<Author> findAll() {
        return repository.findAll();
    }

}
