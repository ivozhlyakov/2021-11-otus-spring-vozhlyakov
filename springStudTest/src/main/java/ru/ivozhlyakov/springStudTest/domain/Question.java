package ru.ivozhlyakov.springStudTest.domain;

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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(getValue());
        int i = 1;
        for (Answer answer : getAnswerList()) {
            builder.append("\n").append(i).append(". ").append(answer.getValue());
            i+=1;
        }
        return builder.toString();
    }
}
