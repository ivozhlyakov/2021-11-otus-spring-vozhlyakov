package ru.ivozhlyakov.exercise10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ivozhlyakov.exercise10.models.Book;

public interface BookRepositoryJpa extends JpaRepository<Book, Long> {

}
