package ru.ivozhlyakov.exercise12.service;


import ru.ivozhlyakov.exercise12.domain.Comment;

import java.util.Optional;

public interface CommentService {
    void createComment(long bookId, String comment);
    Comment save(Comment comment);
    Iterable<Comment> findAll();
    void deleteById(Long id);
    void updateComment(Long id, String comment);
    Optional<Comment> findById(Long id);
}
