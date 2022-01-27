package ru.ivozhlyakov.libraryrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivozhlyakov.libraryrest.models.Genre;

public interface GenreRepositoryJpa extends JpaRepository<Genre, Long> {
}
