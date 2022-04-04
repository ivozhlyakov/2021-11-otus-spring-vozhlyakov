package ru.ivozhlyakov.exercise9.repositories;

import ru.ivozhlyakov.exercise9.models.Book;

import java.util.List;

public interface BookRepositoryJpaCustom {

    List<Book> findAll();
}
