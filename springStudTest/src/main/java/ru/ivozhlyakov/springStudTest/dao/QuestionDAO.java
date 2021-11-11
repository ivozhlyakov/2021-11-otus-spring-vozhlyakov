package ru.ivozhlyakov.springStudTest.dao;

import ru.ivozhlyakov.springStudTest.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class QuestionDAO {

    private List<Question> questionList;

    public QuestionDAO() throws IOException {

    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    private void setQuestionToList(Question question) {
        this.questionList.add(question);
    }

    public void loadObjectList(InputStream file) {
        this.questionList = new LinkedList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            String[] columns = scanner.nextLine().split(";");
            Question question = new Question();
            question.setValue(columns[0]);
            String correctAnswer = columns[columns.length - 1];
            question.setAnswer(columns[1], correctAnswer.equals(columns[1]));
            question.setAnswer(columns[2], correctAnswer.equals(columns[2]));
            question.setAnswer(columns[3], correctAnswer.equals(columns[3]));
            setQuestionToList(question);
        }
    }

}
