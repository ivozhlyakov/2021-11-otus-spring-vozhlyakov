package ru.ivozhlyakov.library.dao;

import ru.ivozhlyakov.library.domain.Book;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BookDao {

    int count();
    Long insert(Book book);
    List<Book> getAll();
    void deleteById(long id);
    void updateBookName(Book book);
    Book getBookByID(Long id);
}
