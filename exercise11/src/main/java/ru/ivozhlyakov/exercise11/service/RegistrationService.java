package ru.ivozhlyakov.exercise11.service;


import ru.ivozhlyakov.exercise11.domain.Registration;
import ru.ivozhlyakov.exercise11.domain.User;

public interface RegistrationService {
    Registration registration(User user);
}
