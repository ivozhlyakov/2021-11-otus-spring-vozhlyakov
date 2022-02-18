package ru.ivozhlyakov.libraryrest.service;

import ru.ivozhlyakov.libraryrest.models.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(long bookId, String comment);
    Comment save(Comment comment);
    List<Comment> findAll();
    void deleteById(Long id);
    void updateComment(Long id, String comment);
}