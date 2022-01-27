package ru.ivozhlyakov.libraryrest.repositories;

import ru.ivozhlyakov.libraryrest.models.Book;

import java.util.List;

public interface BookRepositoryJpaCustom {

    List<Book> findAll();
}
