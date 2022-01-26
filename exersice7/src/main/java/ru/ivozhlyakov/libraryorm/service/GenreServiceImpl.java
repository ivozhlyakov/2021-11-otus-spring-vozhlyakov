package ru.ivozhlyakov.libraryorm.service;

import org.springframework.stereotype.Service;
import ru.ivozhlyakov.libraryorm.models.Genre;
import ru.ivozhlyakov.libraryorm.repositories.GenreRepositoryJpa;

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
