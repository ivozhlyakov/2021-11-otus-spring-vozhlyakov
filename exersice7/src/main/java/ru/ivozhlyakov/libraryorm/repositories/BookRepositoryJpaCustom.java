package ru.ivozhlyakov.libraryorm.repositories;

import ru.ivozhlyakov.libraryorm.models.Book;

import java.util.List;

public interface BookRepositoryJpaCustom {

    List<Book> findAll();
}
