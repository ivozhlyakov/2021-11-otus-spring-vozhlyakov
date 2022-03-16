package ru.ivozhlyakov.exercise12.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ivozhlyakov.exercise12.domain.Book;

import java.util.List;


@RepositoryRestResource(path = "books")
public interface BookRepositoryJpa extends PagingAndSortingRepository<Book, Long> {
    List<Book> findAll();
}