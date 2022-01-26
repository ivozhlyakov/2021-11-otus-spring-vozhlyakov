package ru.ivozhlyakov.libraryorm.repositories;

import org.springframework.stereotype.Repository;
import ru.ivozhlyakov.libraryorm.models.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AuthorsRepositoryJpaImpl implements AuthorsRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery(
                "select a from Author a"
                ,Author.class
        );
        return query.getResultList();
    }
}
