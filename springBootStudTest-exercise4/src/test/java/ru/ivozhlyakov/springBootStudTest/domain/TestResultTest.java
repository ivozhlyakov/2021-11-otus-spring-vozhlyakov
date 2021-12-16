package ru.ivozhlyakov.springBootStudTest.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс TestResult")
class TestResultTest {

    @DisplayName("Должен увеличить количество верных ответов")
    @Test
    void addCorrectAnswer() {
        TestResult result = new TestResult();
        int beforeCount = result.getCorrectAnswerCount();

        result.addCorrectAnswer();
        assertThat(result.getCorrectAnswerCount())
                .isEqualTo(beforeCount + 1);
    }
}