package ru.ivozhlyakov.libraryorm.repositories;


import org.springframework.stereotype.Repository;
import ru.ivozhlyakov.libraryorm.models.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryJpaImpl implements CommentRepositoryJpa{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Comment save(Comment comment) {
            em.persist(comment);
            return comment;
    }

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(em.find(Comment.class,
                id));
    }

    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c ", Comment.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from Comment c " +
                "where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void updateCommentById(long id, String value) {
        Query query = em.createQuery("update Comment c " +
                "set c.comment = :value " +
                "where c.id = :id");
        query.setParameter("value", value);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
