package ru.ivozhlyakov.library.service;

import org.springframework.stereotype.Service;
import ru.ivozhlyakov.library.dao.AuthorDaoImpl;
import ru.ivozhlyakov.library.domain.Author;

@Service
public class AuthorServiceImpl implements AuthorService{

    private AuthorDaoImpl authorDao;

    public AuthorServiceImpl(AuthorDaoImpl genreDao) {
        this.authorDao = genreDao;
    }

    @Override
    public Long insert(Author author) {
        return authorDao.insert(author);
    }

    @Override
    public Author getById(Long id) {
        return authorDao.getById(id);
    }
}
