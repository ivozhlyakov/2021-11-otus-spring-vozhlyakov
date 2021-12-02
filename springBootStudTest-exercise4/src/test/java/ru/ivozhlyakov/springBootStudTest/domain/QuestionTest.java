package ru.ivozhlyakov.springBootStudTest.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс Question")
class QuestionTest {

    @DisplayName("Должен добавить ответ на вопрос")
    @Test
    void getAnswerList() {
        Question question = new Question();
        int beforeCount = question.getAnswerList().size();

        question.setAnswer("answer", false);


        assertThat(question.getAnswerList().size())
                .isEqualTo(beforeCount + 1);
    }

}