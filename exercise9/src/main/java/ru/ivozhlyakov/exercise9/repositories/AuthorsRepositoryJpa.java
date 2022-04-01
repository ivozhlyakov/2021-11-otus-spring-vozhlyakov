package ru.ivozhlyakov.exercise9.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivozhlyakov.exercise9.models.Author;

public interface AuthorsRepositoryJpa extends JpaRepository<Author, Long> {
}
