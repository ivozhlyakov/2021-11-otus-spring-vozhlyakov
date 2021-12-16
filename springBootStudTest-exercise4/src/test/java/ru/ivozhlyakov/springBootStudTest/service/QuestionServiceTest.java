package ru.ivozhlyakov.springBootStudTest.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ActiveProfiles;
import ru.ivozhlyakov.springBootStudTest.dao.QuestionDAO;
import ru.ivozhlyakov.springBootStudTest.domain.Question;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayName("Класс QuestionService")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
class QuestionServiceTest {

    private final String locale = "ru-RU";

    @Mock
    private MessageSource messageSource;

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
        when(messageSource.getMessage(anyString(), any(),any(Locale.class))).thenReturn("dfg");

        System.setIn(new ByteArrayInputStream(
                "surname\nname\nanswer".getBytes())
        );

        Question question = new Question();
        question.setValue("question");
        question.setAnswer("answer", true);

        questionService.setLocale(locale);
        when(questionService.getQuestionList()).thenReturn(Collections.singletonList(question));

        questionService.test();
    }
}