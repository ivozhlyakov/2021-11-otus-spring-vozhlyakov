package ru.ivozhlyakov.exercise10.service;

import org.springframework.stereotype.Service;
import ru.ivozhlyakov.exercise10.models.Author;
import ru.ivozhlyakov.exercise10.repositories.AuthorsRepositoryJpa;

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
