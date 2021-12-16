package ru.ivozhlyakov.springBootStudTest.domain;

public class TestResult {
    private String surname;
    private String name;
    private Integer correctAnswerCount = 0;

    public String getSurmame() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCorrectAnswerCount() {
        return correctAnswerCount;
    }

    public void addCorrectAnswer() {
        this.correctAnswerCount+=1;
    }
}
