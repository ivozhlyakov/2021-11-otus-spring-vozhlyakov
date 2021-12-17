package ru.ivozhlyakov.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.ivozhlyakov.library.domain.Author;
import ru.ivozhlyakov.library.domain.Book;
import ru.ivozhlyakov.library.domain.BookInfo;
import ru.ivozhlyakov.library.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с полной информацией по книгам должно")
@JdbcTest
@Import(BookInfoDao.class)
class BookInfoDaoTest {

    public static final Long EXPECTED_INSERT_ID = 10L;

    @Autowired
    private BookInfoDao dao;

    @DisplayName("добавляет книгу")
    @Test
    void insert() {
        int beforeCounr = dao.getAllBookWithInfo().size();
        Long id = dao.insert(
                new Book(EXPECTED_INSERT_ID, "")
                ,new Author(EXPECTED_INSERT_ID, "")
                ,new Genre(EXPECTED_INSERT_ID, "")
        );
        assertThat(id).isEqualTo(EXPECTED_INSERT_ID);
    }

    @DisplayName("возвращает книгу по заданному идентификатору")
    @Test
    void getBookInfoByID() {
        BookInfo bookInfo = dao.getBookInfoByID(1L);
        assertThat(bookInfo.getBookName()).isNotNull();
    }

    @DisplayName("возвращает весь список книг")
    @Test
    void getAllBookWithInfo() {
        assertThat(dao.getAllBookWithInfo().size() > 0).isEqualTo(Boolean.TRUE);
    }
}