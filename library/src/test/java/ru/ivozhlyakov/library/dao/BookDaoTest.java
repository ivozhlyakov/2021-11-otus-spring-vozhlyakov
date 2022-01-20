package ru.ivozhlyakov.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.ivozhlyakov.library.domain.Author;
import ru.ivozhlyakov.library.domain.Book;
import ru.ivozhlyakov.library.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Dao для работы с книгами должно")
@JdbcTest
@Import(TestDaoConfiguration.class)
class BookDaoTest {

    public static final int EXPECTED_BOOK_COUNT = 3;
    public static final Long EXPECTED_BOOK_ID = 2L;
    public static final String EXPECTED_BOOK_NAME = "BOOK TEST";

    @Autowired
    private BookDaoImpl bookDaoImpl;

    private Book createBook(String name, String authorFio, String genreName) {
        return Book.builder()
                .name(name)
                .author(Author.builder()
                        .brief(authorFio)
                        .build())
                .genre(Genre.builder()
                        .name(genreName)
                        .build())
                .build();
    }

    @DisplayName("возвращает ожидаемое количество книг")
    @Test
    void count() {
        assertThat(bookDaoImpl.count()).isEqualTo(EXPECTED_BOOK_COUNT);
    }

    @DisplayName("добавляет книгу")
    @Test
    void insert() {
        int beforeCount = bookDaoImpl.count();
        Book book = createBook("New_Book", "New_Author", "New_Genre");
        bookDaoImpl.insert(book);
        assertThat(bookDaoImpl.count()).isEqualTo(beforeCount + 1);
    }

    @DisplayName("возвращает книгу по идентификатору")
    @Test
    void getById() {
        Book book = bookDaoImpl.getBookByID(1L);
        assertThat(book.getName()).isNotNull();
    }

    @DisplayName("возвращает список книг")
    @Test
    void getAll() {
        List<Book> books = bookDaoImpl.getAll();
        assertThat(books.size() > 0).isEqualTo(Boolean.TRUE);
    }

    @DisplayName("удалит книгу по заданому идентификатору")
    @Test
    void deleteById() {
        assertThatCode(() -> bookDaoImpl.getBookByID(EXPECTED_BOOK_ID))
                .doesNotThrowAnyException();

        bookDaoImpl.deleteById(EXPECTED_BOOK_ID);

        assertThatThrownBy(() -> bookDaoImpl.getBookByID(EXPECTED_BOOK_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("изменит название книги по идентификатору")
    @Test
    void updateBookName() {
        Book book = Book.builder()
                .id(EXPECTED_BOOK_ID)
                .name(EXPECTED_BOOK_NAME)
                .build();
        bookDaoImpl.updateBookName(book);
        assertThat(bookDaoImpl.getBookByID(EXPECTED_BOOK_ID).getName()).isEqualTo(EXPECTED_BOOK_NAME);
    }
}