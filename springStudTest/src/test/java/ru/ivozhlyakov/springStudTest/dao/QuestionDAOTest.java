package ru.ivozhlyakov.springStudTest.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.ivozhlyakov.springStudTest.domain.Question;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Класс QuestionDAO")
class QuestionDAOTest {

    private QuestionDAO newQuestionDAO() {
        return new QuestionDAO("simpleTesForTheVerbTOBEtoTest.csv", 2);
    }

    @DisplayName("Должен вернуть имя файла с вопросами")
    @Test
    void getFilePath() {
        QuestionDAO questionDAO = newQuestionDAO();
        assertThat(questionDAO.getFilePath()).isNotNull();
    }

    @DisplayName("Должен вернуть необходимое количество правильных ответов")
    @Test
    void getCountToPassExam() {
        QuestionDAO questionDAO = newQuestionDAO();
        assertThat(questionDAO.getCountToPassExam())
                .isEqualTo(2);
    }

    @DisplayName("Должен вернуть список вопросов")
    @Test
    void getQuestionList() {
        QuestionDAO questionDAO = newQuestionDAO();
        assertThat(questionDAO.getQuestionList())
                .isNotNull();
    }

    @DisplayName("Должен добавить вопрос в список")
    @Test
    void setQuestionToList() {
        QuestionDAO questionDAO = newQuestionDAO();
        int beforeSize = questionDAO.getQuestionList().size();

        questionDAO.setQuestionToList(new Question());
        assertThat(questionDAO.getQuestionList().size())
                .isEqualTo(beforeSize + 1);
    }

    @DisplayName("Должен добавить вопрос в список")
    @Test
    void loadQuestionList() {
        QuestionDAO questionDAO = newQuestionDAO();
        int beforeSize = questionDAO.getQuestionList().size();

        questionDAO.loadQuestionList();

        assertThat(questionDAO.getQuestionList())
                .isNotNull();
        assertThat(questionDAO.getQuestionList().size())
                .isEqualTo(beforeSize + 1);

    }
}