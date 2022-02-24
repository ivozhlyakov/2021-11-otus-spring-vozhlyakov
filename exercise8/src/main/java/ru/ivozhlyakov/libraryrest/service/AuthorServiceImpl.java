package ru.ivozhlyakov.libraryrest.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ivozhlyakov.libraryrest.models.Author;
import ru.ivozhlyakov.libraryrest.repositories.AuthorsRepositoryJpa;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService{

    private AuthorsRepositoryJpa repository;

    @Override
    public List<Author> findAll() {
        return repository.findAll();
    }

}
