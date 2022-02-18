package ru.ivozhlyakov.exercise11.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.ivozhlyakov.exercise11.domain.Registration;
import ru.ivozhlyakov.exercise11.domain.User;

@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService {

    @SneakyThrows
    @Override
    public Registration registration(User user) {
        Thread.sleep(1000L);
        System.out.println("[SUCCESS] Успешная регистрация пользователя в системе ");
        return Registration.builder()
                .user(user)
                .isRegistration(true)
                .build();
    }
}
