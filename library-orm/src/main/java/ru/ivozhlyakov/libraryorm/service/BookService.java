package ru.ivozhlyakov.libraryorm.service;

import org.springframework.transaction.annotation.Transactional;
import ru.ivozhlyakov.libraryorm.models.Book;

import java.util.List;

public interface BookService {
    void save(Book book);
    List<Book> findAll();
    void deleteById(Long id);
}
