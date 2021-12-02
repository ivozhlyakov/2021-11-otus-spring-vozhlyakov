package ru.ivozhlyakov.springBootStudTest.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import ru.ivozhlyakov.springBootStudTest.config.TestConfig;
import ru.ivozhlyakov.springBootStudTest.domain.Question;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Класс QuestionDAO")
@SpringBootTest(classes = TestConfig.class)
@ActiveProfiles("test")
@EnableConfigurationProperties
class QuestionDAOTest {

    @Autowired
    private TestConfig config;

    private QuestionDAO newQuestionDAO() {
        return new QuestionDAO(config);
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