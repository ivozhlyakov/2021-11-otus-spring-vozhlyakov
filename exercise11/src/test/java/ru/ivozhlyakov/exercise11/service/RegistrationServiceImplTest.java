package ru.ivozhlyakov.exercise11.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ivozhlyakov.exercise11.domain.Registration;
import ru.ivozhlyakov.exercise11.domain.User;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Проверяем RegistrationServiceImpl")
class RegistrationServiceImplTest {

    @Autowired
    private RegistrationService registrationService;

    @Test
    @DisplayName("должен зарегистрировать пользователя")
    void registration() {
        final User user = User.builder()
                .username("test")
                .build();

        final Registration registration = registrationService.registration(user);
        Assertions.assertThat(registration)
                .isNotNull()
                .extracting(Registration::getUser)
                .isEqualTo(user);
    }
}