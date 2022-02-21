package ru.ivozhlyakov.exercise12.service;

import org.springframework.transaction.annotation.Transactional;
import ru.ivozhlyakov.exercise12.domain.Book;


import java.util.List;
import java.util.Optional;

public interface BookService {
    void save(Book book);
    List<Book> findAll();

    @Transactional(readOnly = true)
    Optional<Book> findById(Long id);

    void deleteById(Long id);
    String showTable();
    void updateNameById(Long id, String name);
}
