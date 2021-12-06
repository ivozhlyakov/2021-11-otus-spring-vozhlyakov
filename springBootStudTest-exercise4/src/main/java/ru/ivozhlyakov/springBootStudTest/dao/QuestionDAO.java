package ru.ivozhlyakov.springBootStudTest.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ru.ivozhlyakov.springBootStudTest.config.TestConfig;
import ru.ivozhlyakov.springBootStudTest.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@Component
public class QuestionDAO {

    private Map<String, List<Question>> questionList;

    private final String filePath;
    private final Integer countToPassExam;

    public QuestionDAO(TestConfig testConfig)  {
        this.filePath = testConfig.getFileName();
        this.countToPassExam = testConfig.getCorrectAnswerCount();

        this.questionList = new HashMap<>();
    }

    public String getFilePath() {
        return filePath;
    }

    public Integer getCountToPassExam() {
        return countToPassExam;
    }

    public List<Question> getQuestionList(String locale) {
        return questionList.get(locale) == null ? Collections.emptyList() : questionList.get(locale);
    }

    public void setQuestionToList(String locale, Question question) {
        List<Question> list = questionList.get(locale) != null ? questionList.get(locale) : new LinkedList<>();
        list.add(question);
        this.questionList.put(locale, list);
    }

    public void loadQuestionList(String locale) {
        try {
            Resource resource = new ClassPathResource(getFilePath());
            InputStream inputStream = resource.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                String[] columns = scanner.nextLine().split(";");
                if (!locale.equals(columns[0])) continue;

                Question question = new Question();
                question.setValue(columns[1]);
                String correctAnswer = columns[columns.length - 1];
                for (int i = 2; i < columns.length - 1; i++) {
                    question.setAnswer(columns[i], correctAnswer.equals(columns[i]));
                }
                setQuestionToList(locale, question);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
