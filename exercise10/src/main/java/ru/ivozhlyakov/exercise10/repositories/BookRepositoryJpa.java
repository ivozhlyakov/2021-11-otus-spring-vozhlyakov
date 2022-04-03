package ru.ivozhlyakov.exercise10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ivozhlyakov.exercise10.models.Book;

public interface BookRepositoryJpa extends JpaRepository<Book, Long>, BookRepositoryJpaCustom {

    @Modifying
    @Query("update Book b " +
            "set b.name = :name " +
            "where b.id = :id")
    void updateNameById(@Param("id") long firstBookId, @Param("name") String new_book_name);

//    @EntityGraph(attributePaths = {"authors", "genres"})
//    @Override
/*
    @Query("select b from Book b " +
            "join b.authors " +
            "join b.genres " +
            "group by b")
*/
//    List<Book> findAll();
}
