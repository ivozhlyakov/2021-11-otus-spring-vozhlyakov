package ru.ivozhlyakov.libraryorm.repositories;

import ru.ivozhlyakov.libraryorm.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryJpa {
    Book save(Book book);

    List<Book> findAll();
/*
    List<Book> findByName(String name);

    void updateNameById(long id, String name);
    void deleteById(long id);
*/
    Optional<Book> findById(long id);
}
