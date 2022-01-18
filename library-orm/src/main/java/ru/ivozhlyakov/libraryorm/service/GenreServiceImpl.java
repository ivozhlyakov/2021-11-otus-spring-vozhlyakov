package ru.ivozhlyakov.libraryorm.service;

import org.springframework.stereotype.Service;
import ru.ivozhlyakov.libraryorm.models.Genre;
import ru.ivozhlyakov.libraryorm.repositories.GenreRepositoryJpaImpl;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepositoryJpaImpl repositoryJpa;

    public GenreServiceImpl(GenreRepositoryJpaImpl repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public List<Genre> findAll() {
        return repositoryJpa.findAll();
    }
}
