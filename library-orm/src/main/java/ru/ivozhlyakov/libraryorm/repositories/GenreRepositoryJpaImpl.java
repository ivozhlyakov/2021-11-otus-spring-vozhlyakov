package ru.ivozhlyakov.libraryorm.repositories;

import org.springframework.stereotype.Repository;
import ru.ivozhlyakov.libraryorm.models.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class GenreRepositoryJpaImpl implements GenreRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = em.createQuery(
                "select g from Genre g"
                ,Genre.class
        );
        return query.getResultList();
    }
}
