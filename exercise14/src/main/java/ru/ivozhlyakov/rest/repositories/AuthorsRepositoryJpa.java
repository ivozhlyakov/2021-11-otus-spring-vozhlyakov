package ru.ivozhlyakov.rest.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ivozhlyakov.rest.domain.Author;


@RepositoryRestResource(path = "authors")
public interface AuthorsRepositoryJpa extends PagingAndSortingRepository<Author, Long> {

}
