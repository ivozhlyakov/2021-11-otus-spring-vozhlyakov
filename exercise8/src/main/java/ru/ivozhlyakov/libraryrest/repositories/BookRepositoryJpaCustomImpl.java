package ru.ivozhlyakov.libraryrest.repositories;


import org.springframework.stereotype.Repository;
import ru.ivozhlyakov.libraryrest.models.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BookRepositoryJpaCustomImpl implements BookRepositoryJpaCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("" +
                "select b from Book b " +
                "join b.authors " +
                "join b.genres " +
                "group by b", Book.class);
        return query.getResultList();
    }

}
