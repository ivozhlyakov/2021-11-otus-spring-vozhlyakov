package ru.ivozhlyakov.libraryrest.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivozhlyakov.libraryrest.models.Book;
import ru.ivozhlyakov.libraryrest.models.Comment;
import ru.ivozhlyakov.libraryrest.repositories.BookRepositoryJpa;
import ru.ivozhlyakov.libraryrest.repositories.CommentRepositoryJpa;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{

    private CommentRepositoryJpa commentRepository;
    private BookRepositoryJpa bookRepository;

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

    public List<Comment> findAll() {
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
    public void updateComment(Long id, String comment)
    {
        Comment commentObj = findById(id).orElseThrow(() -> new Exception("Book no found by id = "+id));
        commentObj.setComment(comment);
        commentRepository.save(commentObj);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }
}
