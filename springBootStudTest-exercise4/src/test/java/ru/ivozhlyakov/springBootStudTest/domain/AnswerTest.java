package ru.ivozhlyakov.springBootStudTest.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс Answer")
class AnswerTest {

    private Answer newAnswer(){
        return new Answer("answer", true);
    }

    @DisplayName("Должен вертуть ответ")
    @Test
    void getValue() {
        Answer answer = newAnswer();
        assertThat(answer.getValue())
                .isNotNull();
    }

    @DisplayName("Должен вернуть результат ответа")
    @Test
    void isCorrect() {
        Answer answer = newAnswer();
        assertThat(answer.isCorrect())
                .isEqualTo(true);
    }

}