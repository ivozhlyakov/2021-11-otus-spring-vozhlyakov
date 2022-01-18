package ru.ivozhlyakov.libraryorm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivozhlyakov.libraryorm.models.Author;
import ru.ivozhlyakov.libraryorm.models.Book;
import ru.ivozhlyakov.libraryorm.models.Genre;
import ru.ivozhlyakov.libraryorm.repositories.BookRepositoryJpaImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    private BookRepositoryJpaImpl repository;
    private String stringTmp;

    public BookServiceImpl(BookRepositoryJpaImpl repository) {
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

    private void addRow(String s) {
        stringTmp = stringTmp.concat(s);
    }

}
