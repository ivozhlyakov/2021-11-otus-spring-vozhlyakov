package ru.ivozhlyakov.libraryorm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivozhlyakov.libraryorm.models.Genre;

import java.util.List;

public interface GenreRepositoryJpa extends JpaRepository<Genre, Long> {
}
