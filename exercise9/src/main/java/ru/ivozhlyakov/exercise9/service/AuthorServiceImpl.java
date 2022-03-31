package ru.ivozhlyakov.exercise9.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ivozhlyakov.exercise9.models.Author;
import ru.ivozhlyakov.exercise9.repositories.AuthorsRepositoryJpa;

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
