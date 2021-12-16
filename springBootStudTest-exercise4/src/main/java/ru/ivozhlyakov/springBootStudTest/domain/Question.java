package ru.ivozhlyakov.springBootStudTest.domain;

import java.util.LinkedList;
import java.util.List;

public class Question {

    private String value;
    private List<Answer> answerList = new LinkedList<Answer>();

    public Question() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswer(String answer, boolean isCorrect) {
        this.answerList.add(new Answer(answer, isCorrect));
    }

}
