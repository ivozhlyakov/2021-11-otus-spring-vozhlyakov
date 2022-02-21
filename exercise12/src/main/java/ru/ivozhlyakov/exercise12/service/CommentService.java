package ru.ivozhlyakov.exercise12.service;


import ru.ivozhlyakov.exercise12.domain.Comment;

public interface CommentService {
    Comment createComment(long bookId, String comment);
    Comment save(Comment comment);
    Iterable<Comment> findAll();
    void deleteById(Long id);
    void updateComment(Long id, String comment);
}
