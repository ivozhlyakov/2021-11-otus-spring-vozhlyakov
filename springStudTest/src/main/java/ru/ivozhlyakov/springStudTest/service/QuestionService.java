package ru.ivozhlyakov.springStudTest.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.ivozhlyakov.springStudTest.dao.QuestionDAO;
import ru.ivozhlyakov.springStudTest.domain.Answer;
import ru.ivozhlyakov.springStudTest.domain.Question;
import ru.ivozhlyakov.springStudTest.domain.TestResult;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

@Service
public class QuestionService {

    private QuestionDAO questionDAO;

    @Value("${result.pass}")
    private String pass;

    @Value("${result.failed}")
    private String failed;

    public QuestionService(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    public List<Question> getQuestionList(){
       return questionDAO.getQuestionList();
   }

    private Integer getCountToPassExam(){
        return questionDAO.getCountToPassExam();
    }

    public void test() {
        TestResult testResult = new TestResult();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your family name: ");
        testResult.setFamilyName(scanner.next());

        System.out.print("Enter your name: ");
        testResult.setName(scanner.next());
        scanner.nextLine();
        System.out.print("\nTest:\n");
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

        String result = failed;
        if (testResult.getCorrectAnswerCount()
                .compareTo(getCountToPassExam()) > -1){
            result = pass;
        }
        System.out.println("\n"+result);
    }

}
