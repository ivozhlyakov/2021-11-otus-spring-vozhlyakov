package ru.ivozhlyakov.springStudTest.dao;

import org.springframework.core.io.Resource;
import ru.ivozhlyakov.springStudTest.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class QuestionDAO {

    private List<Question> questionList;
    private Resource resource;

    public QuestionDAO() throws IOException {

    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    private InputStream getInputStream() throws IOException {
        return resource.getInputStream();
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    private void setQuestionToList(Question question) {
        this.questionList.add(question);
    }

    public void loadObjectList() throws IOException {
        InputStream file = getInputStream();
        this.questionList = new LinkedList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            String[] columns = scanner.nextLine().split(";");
            Question question = new Question();
            question.setValue(columns[0]);
            String correctAnswer = columns[columns.length - 1];
            for (int i= 1; i<columns.length-1; i++) {
                question.setAnswer(columns[i], correctAnswer.equals(columns[i]));
            }
            setQuestionToList(question);
        }
    }

}
