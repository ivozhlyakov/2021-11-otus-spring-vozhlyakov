package ru.ivozhlyakov.libraryorm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ivozhlyakov.libraryorm.models.Comment;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public interface CommentRepositoryJpa extends JpaRepository<Comment, Long> {

    @Modifying
    @Query("update Comment c " +
            "set c.comment = :value " +
            "where c.id = :id")
    void updateCommentById(@Param("id") Long id, @Param("value") String comment);
}
