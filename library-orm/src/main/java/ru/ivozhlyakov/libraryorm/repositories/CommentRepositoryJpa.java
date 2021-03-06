package ru.ivozhlyakov.libraryorm.repositories;

import ru.ivozhlyakov.libraryorm.models.Comment;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public interface CommentRepositoryJpa {
    Comment save(Comment comment);
    Optional<Comment> findById(long id);
    List<Comment> findAll();
    void deleteById(long id);
    void updateCommentById(long id, String value);
}
