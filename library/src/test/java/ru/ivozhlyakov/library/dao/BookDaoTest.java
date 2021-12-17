package ru.ivozhlyakov.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.ivozhlyakov.library.domain.Author;
import ru.ivozhlyakov.library.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Dao для работы с книгами должно")
@JdbcTest
@Import(BookDao.class)
class BookDaoTest {

    public static final int EXPECTED_BOOK_COUNT = 3;
    public static final Long EXPECTED_BOOK_ID = 2L;
    public static final String EXPECTED_BOOK_NAME = "BOOK TEST";

    @Autowired
    private BookDao bookDao;

    @DisplayName("возвращает ожидаемое количество книг")
    @Test
    void count() {
        assertThat(bookDao.count()).isEqualTo(EXPECTED_BOOK_COUNT);
    }

    @DisplayName("добавляет книгу")
    @Test
    void insert() {
        int beforeCount = bookDao.count();
        bookDao.insert(new Book("Book Test"));
        assertThat(bookDao.count()).isEqualTo(beforeCount + 1);
    }

    @DisplayName("возвращает книгу по идентификатору")
    @Test
    void getById() {
        Book book = bookDao.getById(1);
        assertThat(book.getName()).isNotNull();
    }

    @DisplayName("возвращает список книг")
    @Test
    void getAll() {
        List<Book> books = bookDao.getAll();
        assertThat(books.size() > 0).isEqualTo(Boolean.TRUE);
    }

    @DisplayName("удалит книгу по заданому идентификатору")
    @Test
    void deleteById() {
        assertThatCode(() -> bookDao.getById(EXPECTED_BOOK_ID))
                .doesNotThrowAnyException();

        bookDao.deleteById(EXPECTED_BOOK_ID);

        assertThatThrownBy(() -> bookDao.getById(EXPECTED_BOOK_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("изменит название книги по идентификатору")
    @Test
    void updateBookName() {
        Book book = new Book(EXPECTED_BOOK_ID, EXPECTED_BOOK_NAME);
        bookDao.updateBookName(book);
        assertThat(bookDao.getById(EXPECTED_BOOK_ID).getName()).isEqualTo(EXPECTED_BOOK_NAME);
    }
}