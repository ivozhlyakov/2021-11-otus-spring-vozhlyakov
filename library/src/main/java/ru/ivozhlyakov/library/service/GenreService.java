package ru.ivozhlyakov.library.service;

import org.springframework.stereotype.Service;
import ru.ivozhlyakov.library.dao.GenreDao;
import ru.ivozhlyakov.library.domain.Genre;

@Service
public class GenreService {

    private GenreDao genreDao;

    public GenreService(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    public Long insert(String name) {
        return genreDao.insert(new Genre(name));
    }

    public Genre getByName(String name) {
        return genreDao.getByName(name);
    }
}
