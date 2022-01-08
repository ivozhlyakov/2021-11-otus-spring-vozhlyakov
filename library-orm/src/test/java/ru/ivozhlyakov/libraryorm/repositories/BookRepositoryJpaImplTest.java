package ru.ivozhlyakov.libraryorm.repositories;

import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import ru.ivozhlyakov.libraryorm.models.Book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Репозиторий на основе Jpa для работы с книгами ")
@DataJpaTest
@Import(BookRepositoryJpaImpl.class)
class BookRepositoryJpaImplTest {

    private static final int EXPECTED_NUMBER_OF_STUDENTS = 10;
    private static final long FIRST_BOOK_ID = 1L;

    private static final int EXPECTED_QUERIES_COUNT = 3;

    @Autowired
    private BookRepositoryJpaImpl repositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("должен загружать список всех книг с полной информацией о них")
    @Test
    void shouldReturnCorrectStudentsListWithAllInfo() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);


        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
        val books = repositoryJpa.findAll();
        assertThat(books).isNotNull().hasSize(EXPECTED_NUMBER_OF_STUDENTS)
                .allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getAuthors() != null && s.getAuthors().size() > 0)
                .allMatch(s -> s.getGenres() != null);
        System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
    }

    @DisplayName(" должен загружать информацию о нужной книге по ее id")
    @Test
    void shouldFindExpectedBookById() {
        val optionalActualBook = repositoryJpa.findById(FIRST_BOOK_ID);
        val expectedBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(optionalActualBook).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }
}