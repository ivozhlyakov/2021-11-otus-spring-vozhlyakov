package ru.ivozhlyakov.exercise12.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ivozhlyakov.exercise12.domain.Author;


@RepositoryRestResource(path = "authors")
public interface AuthorsRepositoryJpa extends PagingAndSortingRepository<Author, Long> {

}
