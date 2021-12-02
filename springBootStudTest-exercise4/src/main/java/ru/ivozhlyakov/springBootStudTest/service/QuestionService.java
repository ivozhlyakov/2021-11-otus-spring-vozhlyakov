package ru.ivozhlyakov.springBootStudTest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.ivozhlyakov.springBootStudTest.config.TestConfig;
import ru.ivozhlyakov.springBootStudTest.dao.QuestionDAO;
import ru.ivozhlyakov.springBootStudTest.domain.Answer;
import ru.ivozhlyakov.springBootStudTest.domain.Question;
import ru.ivozhlyakov.springBootStudTest.domain.TestResult;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Service
@EnableConfigurationProperties(TestConfig.class)
public class QuestionService {

    @Autowired
    private MessageSource messageSource;

    private QuestionDAO questionDAO;


    private final String testCommentResultPass = "result.pass";

    private final String testCommentResultFailed = "result.failed";

    public QuestionService(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    public List<Question> getQuestionList(){
       return questionDAO.getQuestionList();
   }

    private Integer getCountToPassExam(){
        return questionDAO.getCountToPassExam();
    }

    private void loadObjectList(){
        questionDAO.loadQuestionList();
    }

    public void test() {
        loadObjectList();
        TestResult testResult = new TestResult();
        Scanner scanner = new Scanner(System.in);
        System.out.print(
                messageSource.getMessage("enter.surname",null, Locale.getDefault())
        );
        testResult.setSurname(scanner.next());

        System.out.print(
                messageSource.getMessage("enter.name",null, Locale.getDefault())
        );
        testResult.setName(scanner.next());
        scanner.nextLine();
        System.out.print("\n"
                .concat(messageSource.getMessage("test.title",null, Locale.getDefault())
                        .concat(" \n")));
        for (Question question : getQuestionList()) {
            StringBuilder builder = new StringBuilder(question.getValue());

            builder.append("\n");
            for (Answer answer : question.getAnswerList()) {
                builder.append(" ").append(answer.getValue()).append("    ");
            }
            System.out.println(builder.toString());
            String answer = scanner.nextLine();

            if (answer != null
                    && question.getAnswerList()
                    .stream()
                    .filter(a -> Boolean.TRUE.equals(a.isCorrect()))
                    .findAny().orElse(new Answer("", false))
                    .getValue().equals(answer))
            {
                testResult.addCorrectAnswer();
            }
        }

        String result = messageSource.getMessage(testCommentResultFailed, null, Locale.getDefault());
        if (testResult.getCorrectAnswerCount()
                .compareTo(getCountToPassExam()) > -1){
            result = messageSource.getMessage(testCommentResultPass, null, Locale.getDefault());
        }
        String[] strings = {result};

        System.out.println(messageSource.getMessage("test.ending", strings, Locale.getDefault()));
    }

}
