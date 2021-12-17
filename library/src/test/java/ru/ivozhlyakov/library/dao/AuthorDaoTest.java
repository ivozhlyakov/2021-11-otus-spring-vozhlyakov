package ru.ivozhlyakov.library.dao;

import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.ivozhlyakov.library.domain.Author;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Dao для работы с авторами должно")
@JdbcTest
@Import(AuthorDao.class)
class AuthorDaoTest {

    private static final int EXPECTED_AUTHOR_COUNT = 2;
    private static final String EXPECTED_AUTHOR_NAME = "Stephen King";

    @Autowired
    private AuthorDao authorDao;

    @DisplayName("добавляет автора")
    @Test
    void insert() {
        int beforeCount = authorDao.count();
        authorDao.insert(new Author("Author Test"));
        assertThat(authorDao.count()).isEqualTo(beforeCount + 1);
    }

    @DisplayName("возвращает ожидаемого автора по имени")
    @Test
    void getByName() {
        Author author = authorDao.getByName(EXPECTED_AUTHOR_NAME);
        assertThat(author.getId()).isNotNull();
    }

    @DisplayName("возвращает ожидаемое количество авторов")
    @Test
    void count() {
        assertThat(authorDao.count()).isEqualTo(EXPECTED_AUTHOR_COUNT);
    }
}