package ru.ivozhlyakov.exercise9.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivozhlyakov.exercise9.models.Genre;

public interface GenreRepositoryJpa extends JpaRepository<Genre, Long> {
}
