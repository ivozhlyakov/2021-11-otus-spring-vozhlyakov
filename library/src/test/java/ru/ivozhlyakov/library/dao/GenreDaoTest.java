package ru.ivozhlyakov.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.ivozhlyakov.library.domain.Author;
import ru.ivozhlyakov.library.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Dao для работы с жанрами должно")
@JdbcTest
@Import(GenreDao.class)
class GenreDaoTest {

    private static final String EXPECTED_AUTHOR_NAME = "Genre Test";

    @Autowired
    private GenreDao genreDao;

    @DisplayName("добавляет жанр")
    @Test
    void insert() {
        genreDao.insert(new Genre(EXPECTED_AUTHOR_NAME));
        Genre genre = genreDao.getByName(EXPECTED_AUTHOR_NAME);
        assertThat(genre.getName()).isEqualTo(EXPECTED_AUTHOR_NAME);
    }

    @DisplayName("возвращает ожидаемый жанр")
    @Test
    void getByName() {
        Genre genre = genreDao.getByName("fantasy");
        assertThat(genre.getName()).isNotNull();
    }
}