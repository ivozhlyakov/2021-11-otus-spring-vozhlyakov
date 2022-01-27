package ru.ivozhlyakov.libraryrest.service;

import org.springframework.stereotype.Service;
import ru.ivozhlyakov.libraryrest.models.Genre;
import ru.ivozhlyakov.libraryrest.repositories.GenreRepositoryJpa;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepositoryJpa repositoryJpa;

    public GenreServiceImpl(GenreRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public List<Genre> findAll() {
        return repositoryJpa.findAll();
    }
}
