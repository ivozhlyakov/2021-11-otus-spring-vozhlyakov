package ru.ivozhlyakov.exercise10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivozhlyakov.exercise10.models.Genre;

public interface GenreRepositoryJpa extends JpaRepository<Genre, Long> {
}
