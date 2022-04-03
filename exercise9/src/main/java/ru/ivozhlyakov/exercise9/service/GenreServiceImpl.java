package ru.ivozhlyakov.exercise9.service;

import org.springframework.stereotype.Service;
import ru.ivozhlyakov.exercise9.repositories.GenreRepositoryJpa;
import ru.ivozhlyakov.exercise9.models.Genre;

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
