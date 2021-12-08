package ru.ivozhlyakov.springStudTest.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import ru.ivozhlyakov.springStudTest.domain.Question;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


@Component

public class QuestionDAO {

    private List<Question> questionList;

    private final String filePath;
    private final Integer countToPassExam;

    public QuestionDAO(@Value("${file.name}") String fileName, @Value("${passExam.count}") Integer countToPassExam)  {
        this.filePath = fileName;
        this.countToPassExam = countToPassExam;
        this.questionList = new LinkedList<>();
    }

    public String getFilePath() {
        return filePath;
    }

    public Integer getCountToPassExam() {
        return countToPassExam;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionToList(Question question) {
        this.questionList.add(question);
    }

    public void loadQuestionList() {
        try {
            Resource resource = new ClassPathResource(getFilePath());
            InputStream inputStream = resource.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                String[] columns = scanner.nextLine().split(";");
                Question question = new Question();
                question.setValue(columns[0]);
                String correctAnswer = columns[columns.length - 1];
                for (int i = 1; i < columns.length - 1; i++) {
                    question.setAnswer(columns[i], correctAnswer.equals(columns[i]));
                }
                setQuestionToList(question);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
