package ru.ivozhlyakov.exercise10.repositories;

import ru.ivozhlyakov.exercise10.models.Book;

import java.util.List;

public interface BookRepositoryJpaCustom {

    List<Book> findAll();
}
