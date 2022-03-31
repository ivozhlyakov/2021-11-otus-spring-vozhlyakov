package ru.ivozhlyakov.exercise11.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ivozhlyakov.exercise11.domain.User;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Проверяем PasswordServiceImpl")
@SpringBootTest
class PasswordServiceImplTest {

    @Autowired
    private PasswordServiceImpl passwordService;

    @Test
    @DisplayName("должен пройти проверку пароля")
    void check() {
        User user = User.builder()
                .username("test")
                .build();

        Assertions.assertThat(passwordService.check(user))
        .isNotNull()
        .extracting(User::isPassValid)
        .isNotNull()
                ;

    }
}