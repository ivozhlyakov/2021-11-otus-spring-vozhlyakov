package ru.ivozhlyakov.library.service;

import org.springframework.stereotype.Service;
import ru.ivozhlyakov.library.dao.GenreDaoImpl;
import ru.ivozhlyakov.library.domain.Genre;

@Service
public class GenreServiceImpl implements GenreService{

    private GenreDaoImpl genreDaoImpl;

    public GenreServiceImpl(GenreDaoImpl genreDaoImpl) {
        this.genreDaoImpl = genreDaoImpl;
    }

    @Override
    public Long insert(Genre genre) {
        return genreDaoImpl.insert(genre);
    }

    @Override
    public Genre getByName(Long id) {
        return genreDaoImpl.getById(id);
    }
}
