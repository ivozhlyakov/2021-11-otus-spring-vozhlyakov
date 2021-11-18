package ru.ivozhlyakov.springStudTest.domain;

public class TestResult {
    private String familyName;
    private String name;
    private Integer correctAnswerCount = 0;

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
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
