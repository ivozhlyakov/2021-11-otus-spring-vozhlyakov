package ru.ivozhlyakov.springStudTest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;
import ru.ivozhlyakov.springStudTest.dao.QuestionDAO;
import ru.ivozhlyakov.springStudTest.domain.Answer;
import ru.ivozhlyakov.springStudTest.domain.Question;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Класс QuestionService")
@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @Mock
    private QuestionDAO questionDAO;
    @InjectMocks
    private QuestionService questionService;

    @DisplayName("Должен вернуть список вопросов")
    @Test
    void getQuestionList() {
        assertThat(questionService.getQuestionList())
                .isNotNull();
    }


    @DisplayName("Должен прогнаться тест")
    @Test
    void test1() {
        System.setIn(new ByteArrayInputStream(
                "surname\nname\nanswer".getBytes())
        );
        Question question = new Question();
        question.setValue("question");
        question.setAnswer("answer", true);

        when(questionDAO.getQuestionList()).thenReturn(Collections.singletonList(question));

        questionService.test();
    }
}