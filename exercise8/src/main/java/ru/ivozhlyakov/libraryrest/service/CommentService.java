package ru.ivozhlyakov.libraryrest.service;

import org.springframework.transaction.annotation.Transactional;
import ru.ivozhlyakov.libraryrest.models.Book;
import ru.ivozhlyakov.libraryrest.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    void createComment(long bookId, String comment);
    Comment save(Comment comment);
    List<Comment> findAll();
    void deleteById(Long id);
    void updateComment(Long id, String comment);

    @Transactional(readOnly = true)
    Optional<Comment> findById(Long id);
}
