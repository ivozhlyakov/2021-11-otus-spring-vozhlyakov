package ru.ivozhlyakov.exercise10.service;

import org.springframework.transaction.annotation.Transactional;
import ru.ivozhlyakov.exercise10.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book save(Book book);
    List<Book> findAll();

    @Transactional(readOnly = true)
    Optional<Book> findById(Long id);

    void deleteById(Long id);
    String showTable();
    void updateNameById(Long id, String name);
}
