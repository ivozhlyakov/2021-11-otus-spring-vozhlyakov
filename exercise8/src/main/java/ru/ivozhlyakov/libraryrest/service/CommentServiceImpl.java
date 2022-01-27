package ru.ivozhlyakov.libraryrest.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivozhlyakov.libraryrest.models.Book;
import ru.ivozhlyakov.libraryrest.models.Comment;
import ru.ivozhlyakov.libraryrest.repositories.BookRepositoryJpa;
import ru.ivozhlyakov.libraryrest.repositories.CommentRepositoryJpa;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentRepositoryJpa commentRepository;
    private BookRepositoryJpa bookRepository;

    public CommentServiceImpl(CommentRepositoryJpa commentRepository, BookRepositoryJpa bookRepository) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    @Override
    public Comment createComment(long bookId, @NonNull String comment) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        return commentRepository.save(
                new Comment(
                        null
                        ,comment
                        , book
                )
        );
    }



    @Transactional
    @Override
    public Comment save(Comment comment){
        return commentRepository.save(comment);
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void updateComment(Long id, String comment) {
        commentRepository.updateCommentById(id, comment);
    }
}
