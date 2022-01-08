package ru.ivozhlyakov.libraryorm.repositories;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ivozhlyakov.libraryorm.models.Book;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class BookRepositoryJpaImpl implements BookRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Book save(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class,
                id));
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }


}
