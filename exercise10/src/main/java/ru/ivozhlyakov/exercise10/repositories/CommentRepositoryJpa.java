package ru.ivozhlyakov.exercise10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ivozhlyakov.exercise10.models.Comment;

public interface CommentRepositoryJpa extends JpaRepository<Comment, Long> {

}
