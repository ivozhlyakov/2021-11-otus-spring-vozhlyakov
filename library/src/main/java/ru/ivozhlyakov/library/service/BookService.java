package ru.ivozhlyakov.library.service;

import ru.ivozhlyakov.library.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();
    Long insert(Book book);
    Book getById(Long id);
    void deleteByID(Long id);
    void updateBookNameByID(Book book);
}
