package ru.ivozhlyakov.rest.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ivozhlyakov.rest.domain.Genre;


@RepositoryRestResource(path = "genres")
public interface GenreRepositoryJpa extends PagingAndSortingRepository<Genre, Long> {
}
