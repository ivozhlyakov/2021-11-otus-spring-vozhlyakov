package ru.ivozhlyakov.libraryorm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivozhlyakov.libraryorm.models.Author;

import java.util.List;

public interface AuthorsRepositoryJpa extends JpaRepository<Author, Long> {
}
