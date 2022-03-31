package ru.ivozhlyakov.exercise9.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivozhlyakov.exercise9.models.Author;
import ru.ivozhlyakov.exercise9.models.Book;
import ru.ivozhlyakov.exercise9.models.Genre;
import ru.ivozhlyakov.exercise9.repositories.BookRepositoryJpa;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService{

    private BookRepositoryJpa repository;

    @Transactional
    @Override
    public Book save(Book book){
        return repository.save(book);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @SneakyThrows
    @Transactional
    @Override
    public void updateNameById(Long id, String name) {
        Book book = findById(id).orElseThrow(() -> new Exception("Book no found by id = "+id));
        book.setName(name);
        repository.save(book);
    }

}
