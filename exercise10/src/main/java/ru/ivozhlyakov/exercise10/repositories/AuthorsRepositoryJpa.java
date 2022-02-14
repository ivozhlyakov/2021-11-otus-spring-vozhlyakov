package ru.ivozhlyakov.exercise10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivozhlyakov.exercise10.models.Author;

public interface AuthorsRepositoryJpa extends JpaRepository<Author, Long> {
}
