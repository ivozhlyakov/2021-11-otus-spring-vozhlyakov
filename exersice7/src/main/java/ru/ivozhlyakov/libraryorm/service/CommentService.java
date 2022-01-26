package ru.ivozhlyakov.libraryorm.service;

import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;
import ru.ivozhlyakov.libraryorm.models.Book;
import ru.ivozhlyakov.libraryorm.models.Comment;

import java.util.List;

public interface CommentService {
    void createComment(long bookId,String comment);
    void save(Comment comment);
    List<Comment> findAll();
    void deleteById(Long id);
    void updateComment(Long id, String comment);
}
