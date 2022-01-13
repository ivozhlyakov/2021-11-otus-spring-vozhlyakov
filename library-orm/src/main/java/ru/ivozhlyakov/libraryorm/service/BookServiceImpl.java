package ru.ivozhlyakov.libraryorm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivozhlyakov.libraryorm.models.Book;
import ru.ivozhlyakov.libraryorm.repositories.BookRepositoryJpaImpl;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private BookRepositoryJpaImpl repository;

    public BookServiceImpl(BookRepositoryJpaImpl repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void save(Book book){
        repository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
