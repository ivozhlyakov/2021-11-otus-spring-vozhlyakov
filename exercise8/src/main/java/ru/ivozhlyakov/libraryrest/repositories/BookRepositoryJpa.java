package ru.ivozhlyakov.libraryrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ivozhlyakov.libraryrest.models.Book;

public interface BookRepositoryJpa extends JpaRepository<Book, Long>{

/*    @Modifying
    @Query("update Book b " +
            "set b.name = :name " +
            "where b.id = :id")
    void updateNameById(@Param("id") long firstBookId, @Param("name") String new_book_name);
*/
}
