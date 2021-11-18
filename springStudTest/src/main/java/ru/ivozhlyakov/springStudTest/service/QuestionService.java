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
    private TestResult testResult;

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
        this.testResult = new TestResult();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your family name: ");
        this.testResult.setFamilyName(scanner.next());

        System.out.print("Enter your name: ");
        this.testResult.setName(scanner.next());
        for (Question question : getQuestionList()) {
            StringBuilder builder = new StringBuilder(question.getValue());
            int i = 1;
            builder.append("\n");
            for (Answer answer : question.getAnswerList()) {
                builder.append(i).append(". ").append(answer.getValue()).append("    ");
                i += 1;
            }
            System.out.println(builder.toString());
            String answer = scanner.next();

            if (answer != null
                    && question.getAnswerList()
                    .stream()
                    .filter(a -> a.isCorrect())
                    .findAny().get()
                    .getValue().equals(answer))
            {
                this.testResult.addCorrectAnswer();
            }
        }

        checkResult();
    }

    private void checkResult() {
        String result = failed;
        if (testResult.getCorrectAnswerCount()
                .compareTo(getCountToPassExam()) > -1){
         result = pass;
        }
        System.out.println(result);
    }
}
