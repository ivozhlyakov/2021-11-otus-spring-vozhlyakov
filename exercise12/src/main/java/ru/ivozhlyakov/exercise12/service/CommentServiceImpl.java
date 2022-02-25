package ru.ivozhlyakov.exercise12.service;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivozhlyakov.exercise12.domain.Book;
import ru.ivozhlyakov.exercise12.domain.Comment;
import ru.ivozhlyakov.exercise12.repositories.BookRepositoryJpa;
import ru.ivozhlyakov.exercise12.repositories.CommentRepositoryJpa;

import java.util.Optional;

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
    public void createComment(long bookId, @NonNull String comment) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        commentRepository.save(
                new Comment(
                        null
                        , comment
                        , book
                )
        );
    }



    @Transactional
    @Override
    public Comment save(Comment comment){
        return commentRepository.save(comment);
    }

    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @SneakyThrows
    @Transactional
    @Override
    public void updateComment(Long id, String comment) {
        Comment commentFind = commentRepository.findById(id).orElseThrow(() -> new Exception("Comment not Found by id = " + id));
        commentFind.setComment(comment);
        commentRepository.save(commentFind);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.empty();
    }
}
