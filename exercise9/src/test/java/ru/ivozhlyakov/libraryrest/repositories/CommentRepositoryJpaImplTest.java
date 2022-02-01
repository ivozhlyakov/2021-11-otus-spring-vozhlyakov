package ru.ivozhlyakov.libraryrest.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.ivozhlyakov.exercise9.models.Author;
import ru.ivozhlyakov.exercise9.models.Book;
import ru.ivozhlyakov.exercise9.models.Comment;
import ru.ivozhlyakov.exercise9.models.Genre;
import ru.ivozhlyakov.exercise9.repositories.CommentRepositoryJpa;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с комментариями ")
@DataJpaTest
class CommentRepositoryJpaImplTest {

    @Autowired
    private CommentRepositoryJpa repositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("должен сохранить комментарий")
    @Test
    void shouldSaveComment() {
        val comment = new Comment(
                null,
                "test"
                , new Book( "book"
                , Collections.singletonList(new Author("author"))
                , Collections.singletonList(new Genre("genre"))
        )

        );
        repositoryJpa.save(comment);
        assertThat(comment.getId()).isNotNull();

        val actualComment = em.find(Comment.class, comment.getId());
        assertThat(actualComment).isNotNull()
                .matches(comment1 -> !comment1.getComment().equals(""));
    }

    @DisplayName("должен найти определенный комментарий")
    @Test
    void findById() {
        val comment = new Comment(
                null,
                "test3"
                , new Book("book3"
                , Collections.singletonList(new Author("author3"))
                , Collections.singletonList(new Genre("genre3"))
        )

        );
        repositoryJpa.save(comment);
        assertThat(comment.getId()).isNotNull();

        val optionalActualBook = repositoryJpa.findById(comment.getId());
        val expectedComment = em.find(Comment.class, comment.getId());
        assertThat(optionalActualBook).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @DisplayName("должен найти все комментарии")
    @Test
    void findAll() {
        val comment1 = new Comment(
                null,
                "test1"
                , new Book("book1"
                , Collections.singletonList(new Author("author1"))
                , Collections.singletonList(new Genre("genre1"))
        )
        );

        val comment2 = new Comment(
                null,
                "test2"
                , new Book("book2"
                , Collections.singletonList(new Author("author2"))
                , Collections.singletonList(new Genre("genre2"))
        )
        );

        em.persist(comment1);
        em.persist(comment2);

        assertThat(repositoryJpa.findAll()).contains(comment1, comment2);

    }

    @DisplayName("должен удалить определенный комментарий")
    @Test
    void deleteById() {
        val comment = new Comment(
                null,
                "test5"
                , new Book("book5"
                , Collections.singletonList(new Author("author5"))
                , Collections.singletonList(new Genre("genre5"))
        )
        );

        em.persist(comment);
        em.detach(comment);

        assertThat(comment.getId()).isNotNull();
        repositoryJpa.deleteById(comment.getId());

        assertThat(em.find(Comment.class, comment.getId())).isNull();
    }

    @DisplayName("должен изменить комментарий")
    @Test
    void updateComment() {
        val comment = new Comment(
                null,
                "test6"
                , new Book("book5"
                , Collections.singletonList(new Author("author6"))
                , Collections.singletonList(new Genre("genre6"))
        )
        );
        val updateComment = "update comment";
        em.persist(comment);
        em.detach(comment);
        repositoryJpa.updateCommentById(comment.getId(), updateComment);

        val expectedComment = em.find(Comment.class, comment.getId());
        assertThat(expectedComment).isNotNull()
                .matches(c -> c.getComment().equals(updateComment));
    }
}