package ru.ivozhlyakov.exercise11.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.ivozhlyakov.exercise11.domain.User;

import java.util.Random;

@Service("passwordService")
public class PasswordServiceImpl implements PasswordService {

    private final Random random = new Random();

    @SneakyThrows
    @Override
    public User check(User user) {
        Thread.sleep(1000L);
        System.out.println("Проверка пароля на соответствие политики безопасности...");
        user.setPassValid(random.nextBoolean());
        return user;
    }
}
