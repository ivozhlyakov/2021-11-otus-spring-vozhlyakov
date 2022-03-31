package ru.ivozhlyakov.exercise9.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivozhlyakov.exercise9.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByLogin(String login);
}
