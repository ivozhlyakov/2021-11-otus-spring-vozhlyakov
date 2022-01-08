package ru.ivozhlyakov.libraryorm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivozhlyakov.libraryorm.models.Book;
import ru.ivozhlyakov.libraryorm.repositories.BookRepositoryJpaImpl;

import java.util.List;

@Service
public class BookService {

    private BookRepositoryJpaImpl repository;

    public BookService(BookRepositoryJpaImpl repository) {
        this.repository = repository;
    }

    @Transactional
    public void save(Book book){
        repository.save(book);
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public String showAll() {
        return findAll().toString();
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
