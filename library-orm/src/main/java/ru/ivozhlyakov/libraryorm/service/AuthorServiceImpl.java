package ru.ivozhlyakov.libraryorm.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivozhlyakov.libraryorm.models.Author;
import ru.ivozhlyakov.libraryorm.models.Book;
import ru.ivozhlyakov.libraryorm.repositories.AuthorsRepositoryJpaImpl;
import ru.ivozhlyakov.libraryorm.repositories.BookRepositoryJpaImpl;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService{

    private AuthorsRepositoryJpaImpl repository;

    public AuthorServiceImpl(AuthorsRepositoryJpaImpl repository) {
        this.repository = repository;
    }

    @Override
    public List<Author> findAll() {
        return repository.findAll();
    }

}
