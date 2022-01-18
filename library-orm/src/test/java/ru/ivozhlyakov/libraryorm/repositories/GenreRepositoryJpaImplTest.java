package ru.ivozhlyakov.libraryorm.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий на основе Jpa для работы с жанрами")
@DataJpaTest
@Import(GenreRepositoryJpaImpl.class)
class GenreRepositoryJpaImplTest {

    @Autowired
    private GenreRepositoryJpaImpl repositoryJpa;

    @Test
    @DisplayName("должен вернуть все жанары")
    void findAll() {
        assertThat(repositoryJpa.findAll()).isNotNull();
    }
}