package ru.ivozhlyakov.exercise11;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ivozhlyakov.exercise11.domain.Registration;
import ru.ivozhlyakov.exercise11.domain.User;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Exercise11ApplicationTests {

    @Autowired
    private RegistrationGateway gateway;

    @Test
    void contextLoads() {
        final Registration registration = gateway.registration(User.builder()
                .username("FirstUser")
                .password("1234")
                .build());

        assertThat(registration)
                .isNotNull();
    }

}
