package ru.ivozhlyakov.exercise12.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ivozhlyakov.exercise12.domain.Book;


@RepositoryRestResource(path = "books")
public interface BookRepositoryJpa extends PagingAndSortingRepository<Book, Long>, BookRepositoryJpaCustom {

    @Modifying
    @Query("update Book b " +
            "set b.name = :name " +
            "where b.id = :id")
    void updateNameById(@Param("id") long firstBookId, @Param("name") String new_book_name);

}
