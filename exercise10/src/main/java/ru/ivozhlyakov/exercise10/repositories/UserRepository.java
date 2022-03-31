package ru.ivozhlyakov.exercise10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivozhlyakov.exercise10.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByLogin(String login);
}
