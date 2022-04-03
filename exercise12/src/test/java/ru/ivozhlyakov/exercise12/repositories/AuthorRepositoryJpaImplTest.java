package ru.ivozhlyakov.exercise12.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с авторами")
@DataJpaTest
class AuthorRepositoryJpaImplTest {

    @Autowired
    private AuthorsRepositoryJpa repositoryJpa;

    @Test
    @DisplayName("должен вернуть всех авторов")
    void findAll() {
        assertThat(repositoryJpa.findAll())
                .isNotNull()
        ;
    }
}