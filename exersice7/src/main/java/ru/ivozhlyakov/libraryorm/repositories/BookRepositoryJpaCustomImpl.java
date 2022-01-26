package ru.ivozhlyakov.libraryorm.repositories;


import org.springframework.stereotype.Repository;
import ru.ivozhlyakov.libraryorm.models.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

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
