package ru.ivozhlyakov.springStudTest.domain;

public class Answer {
    private String value;
    private boolean isCorrect;

    public Answer(String value, boolean isCorrect) {
        this.value = value;
        this.isCorrect = isCorrect;
    }

    public String getValue() {
        return value;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

}
