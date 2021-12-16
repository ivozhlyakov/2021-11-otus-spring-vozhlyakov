package ru.ivozhlyakov.library.service;

import org.springframework.stereotype.Service;
import ru.ivozhlyakov.library.dao.AuthorDao;
import ru.ivozhlyakov.library.domain.Author;
import ru.ivozhlyakov.library.domain.Genre;

@Service
public class AuthorService {

    private AuthorDao authorDao;

    public AuthorService(AuthorDao genreDao) {
        this.authorDao = genreDao;
    }

    public Long insert(String name) {
        return authorDao.insert(new Author(name));
    }

    public Author getByName(String name) {
        return authorDao.getByName(name);
    }
}
