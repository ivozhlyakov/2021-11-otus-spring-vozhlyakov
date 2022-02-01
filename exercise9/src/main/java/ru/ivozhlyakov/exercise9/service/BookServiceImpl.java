package ru.ivozhlyakov.exercise9.service;

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

@Service
public class BookServiceImpl implements BookService{

    private BookRepositoryJpa repository;
    private String stringTmp;

    public BookServiceImpl(BookRepositoryJpa repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void save(Book book){
        repository.save(book);
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

    @Transactional(readOnly = true)
    @Override
    public String showTable() {
        stringTmp = "";
        repository.findAll().forEach(book -> {
            List<Author> authors = Optional.ofNullable(book.getAuthors()).orElse(Collections.singletonList(new Author()));
            List<Genre> genres = Optional.ofNullable(book.getGenres()).orElse(Collections.singletonList(new Genre()));

            addRow("\n"
                    +"book.id: "+book.getId() + ", "
                    +"book.name: "+book.getName()+", "
                    +"book.authors: "+authors
                    .stream()
                    .map(Author::getBrief)
                    .collect(Collectors.toList())+", "
                    +"book.genres: "+genres
                    .stream()
                    .map(Genre::getName)
                    .collect(Collectors.toList())
            );
        });

        return stringTmp;
    }

    @Transactional
    @Override
    public void updateNameById(Long id, String name) {
        repository.updateNameById(id, name);
    }

    private void addRow(String s) {
        stringTmp = stringTmp.concat(s);
    }

}
