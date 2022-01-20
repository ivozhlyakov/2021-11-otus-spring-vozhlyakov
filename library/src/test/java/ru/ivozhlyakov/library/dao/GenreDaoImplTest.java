package ru.ivozhlyakov.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.ivozhlyakov.library.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с жанрами должно")
@JdbcTest
@Import(TestDaoConfiguration.class)
class GenreDaoImplTest {

    private static final String EXPECTED_AUTHOR_NAME = "Genre Test";

    @Autowired
    private GenreDaoImpl genreDaoImpl;

    @DisplayName("добавляет жанр")
    @Test
    void insert() {
        Long genre_id = genreDaoImpl.insert(Genre.builder().name(EXPECTED_AUTHOR_NAME).build());
        Genre genre = genreDaoImpl.getById(genre_id);
        assertThat(genre.getName()).isEqualTo(EXPECTED_AUTHOR_NAME);
    }

    @DisplayName("возвращает ожидаемый жанр")
    @Test
    void getByName() {
        Genre genre = genreDaoImpl.getById(1L);
        assertThat(genre.getName()).isNotNull();
    }
}