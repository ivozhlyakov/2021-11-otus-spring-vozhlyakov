package ru.ivozhlyakov.libraryrest.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ivozhlyakov.libraryrest.models.Genre;
import ru.ivozhlyakov.libraryrest.repositories.GenreRepositoryJpa;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

    private GenreRepositoryJpa repositoryJpa;

    @Override
    public List<Genre> findAll() {
        return repositoryJpa.findAll();
    }
}
