package ru.ivozhlyakov.exercise12.health;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.ivozhlyakov.exercise12.repositories.BookRepositoryJpa;

@RequiredArgsConstructor
@Component
public class BookCountHealthIndicator implements HealthIndicator {

    public static final String TOTAL = "book_total";
    private final BookRepositoryJpa bookRepository;

    @Override
    public Health health() {
        return Health.up()
                .withDetail(TOTAL, bookRepository.count())
                .build();
    }
}
