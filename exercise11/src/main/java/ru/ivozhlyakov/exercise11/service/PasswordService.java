package ru.ivozhlyakov.exercise11.service;

import ru.ivozhlyakov.exercise11.domain.User;

public interface PasswordService {
    User check(User user);
}
