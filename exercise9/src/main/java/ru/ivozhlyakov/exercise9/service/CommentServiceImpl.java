package ru.ivozhlyakov.exercise9.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivozhlyakov.exercise9.models.Book;
import ru.ivozhlyakov.exercise9.models.Comment;
import ru.ivozhlyakov.exercise9.repositories.BookRepositoryJpa;
import ru.ivozhlyakov.exercise9.repositories.CommentRepositoryJpa;

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

}
