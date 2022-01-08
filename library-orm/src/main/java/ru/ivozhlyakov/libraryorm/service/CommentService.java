package ru.ivozhlyakov.libraryorm.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivozhlyakov.libraryorm.models.Book;
import ru.ivozhlyakov.libraryorm.models.Comment;
import ru.ivozhlyakov.libraryorm.repositories.BookRepositoryJpaImpl;
import ru.ivozhlyakov.libraryorm.repositories.CommentRepositoryJpaImpl;

import java.util.List;

@Service
public class CommentService {

    private CommentRepositoryJpaImpl commentRepository;
    private BookRepositoryJpaImpl bookRepository;

    public CommentService(CommentRepositoryJpaImpl commentRepository, BookRepositoryJpaImpl bookRepository) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void createComment(long bookId, @NonNull String comment) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        commentRepository.save(
                new Comment(
                        null
                        ,comment
                        , book
                )
        );
    }



    @Transactional
    public void save(Comment comment){
        commentRepository.save(comment);
    }

    @Transactional
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
