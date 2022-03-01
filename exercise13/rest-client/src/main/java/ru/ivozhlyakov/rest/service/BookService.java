package ru.ivozhlyakov.rest.service;

import ru.ivozhlyakov.rest.dto.Book;

import java.util.List;

public interface BookService {
    Book getBookById(long id);

    List<Book> getAllBooks();
}
