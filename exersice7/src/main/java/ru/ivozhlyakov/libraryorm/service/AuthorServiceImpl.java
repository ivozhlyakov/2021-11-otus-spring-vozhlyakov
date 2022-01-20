package ru.ivozhlyakov.libraryorm.service;

import org.springframework.stereotype.Service;
import ru.ivozhlyakov.libraryorm.models.Author;
import ru.ivozhlyakov.libraryorm.repositories.AuthorsRepositoryJpa;

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
