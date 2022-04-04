package ru.ivozhlyakov.exercise12.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.ivozhlyakov.exercise12.domain.Author;
import ru.ivozhlyakov.exercise12.domain.Book;
import ru.ivozhlyakov.exercise12.domain.Genre;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с книгами ")
@DataJpaTest
class BookRepositoryJpaImplTest {

    private static final int EXPECTED_NUMBER_OF_BOOKS = 3;
    private static final long FIRST_BOOK_ID = 1L;

    private static final int EXPECTED_QUERIES_COUNT = 3;

    @Autowired
    private BookRepositoryJpa repositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("должен загружать список всех книг с полной информацией о них")
    @Test
    void shouldReturnCorrectBooksListWithAllInfo() {
        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
        val books = repositoryJpa.findAll();
        assertThat(books).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS)
                .allMatch(book -> !book.getName().equals(""))
                .allMatch(book -> book.getAuthors() != null && book.getAuthors().size() > 0)
                .allMatch(book -> book.getGenres() != null);
    }

    @DisplayName(" должен загружать информацию о нужной книге по ее id")
    @Test
    void shouldFindExpectedBookById() {
        val optionalActualBook = repositoryJpa.findById(FIRST_BOOK_ID);
        val expectedBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(optionalActualBook).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName(" должен корректно сохранять всю информацию о книге")
    @Test
    void shouldSaveAllBookInfo() {
        val author = new Author( "Author");
        val genre = new Genre( "Genre");
        val authors = Collections.singletonList(author);
        val genres = Collections.singletonList(genre);

        val book = new Book("BookTest", authors, genres);
        repositoryJpa.save(book);
        assertThat(book.getId()).isGreaterThan(0);

        val actualBook = em.find(Book.class, book.getId());
        assertThat(actualBook).isNotNull().matches(book1 -> !book1.getName().equals(""))
                .matches(book1 -> book1.getAuthors() != null && book1.getAuthors().size() > 0 && book1.getAuthors().get(0).getId() > 0)
                .matches(book1 -> book1.getGenres() != null);
    }

    @DisplayName(" должен корректно изменить информацию о книге")
    @Test
    void shouldUpdateNameBook() {
        val author = new Author( "New_Author");
        val genre = new Genre( "New_Genre");
        val authors = Collections.singletonList(author);
        val genres = Collections.singletonList(genre);
        val book = new Book(1L, "New_Namw_Book", authors, genres);
        repositoryJpa.save(book);

        val actualBook = em.find(Book.class, book.getId());
        assertThat(actualBook).isNotNull()
                .matches(book1 -> book1.getName().equals("New_Namw_Book"))
                .matches(book1 -> book1.getGenres().get(0).getName().equals(genres.get(0).getName()))
                .matches(book1 -> book1.getAuthors().get(0).getBrief().equals(authors.get(0).getBrief()))
        ;
    }

    @DisplayName(" должен изменять название книги по ее id")
    @Test
    void shouldUpdateBookNameById() {
        val firstBook = em.find(Book.class, FIRST_BOOK_ID);
        String oldName = firstBook.getName();
        em.detach(firstBook);

        repositoryJpa.save(Book.builder().id(FIRST_BOOK_ID).name("New_Book_Name").build());
        val updatedStudent = em.find(Book.class, FIRST_BOOK_ID);

        assertThat(updatedStudent.getName()).isNotEqualTo(oldName).isEqualTo("New_Book_Name");
    }

    @DisplayName(" должен удалять книгу по ее id")
    @Test
    void shouldDeleteBookNameById() {
        val firstBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(firstBook).isNotNull();
        em.detach(firstBook);

        repositoryJpa.deleteById(FIRST_BOOK_ID);
        val deletedStudent = em.find(Book.class, FIRST_BOOK_ID);

        assertThat(deletedStudent).isNull();
    }
}