package ru.ivozhlyakov.exercise9.service;

import ru.ivozhlyakov.exercise9.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    void createComment(long bookId, String comment);
    List<Comment> findAll();
    void deleteById(Long id);
    Comment save(Comment comment);
}
