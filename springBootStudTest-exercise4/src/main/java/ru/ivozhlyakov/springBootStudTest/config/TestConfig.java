package ru.ivozhlyakov.springBootStudTest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "test-config")
@Component
public class TestConfig {
    private String fileName;
    private Integer correctAnswerCount;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getCorrectAnswerCount() {
        return correctAnswerCount;
    }

    public void setCorrectAnswerCount(Integer correctAnswerCount) {
        this.correctAnswerCount = correctAnswerCount;
    }

}
