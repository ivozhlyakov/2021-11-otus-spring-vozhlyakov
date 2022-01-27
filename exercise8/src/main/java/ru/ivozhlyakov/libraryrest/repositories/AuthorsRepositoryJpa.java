package ru.ivozhlyakov.libraryrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivozhlyakov.libraryrest.models.Author;

public interface AuthorsRepositoryJpa extends JpaRepository<Author, Long> {
}
