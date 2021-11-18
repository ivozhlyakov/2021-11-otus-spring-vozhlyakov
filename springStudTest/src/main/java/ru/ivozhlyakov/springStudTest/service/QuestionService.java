package ru.ivozhlyakov.springStudTest.service;

import org.springframework.core.io.Resource;
import ru.ivozhlyakov.springStudTest.dao.QuestionDAO;
import ru.ivozhlyakov.springStudTest.domain.Answer;
import ru.ivozhlyakov.springStudTest.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class QuestionService {

    private QuestionDAO questionDAO;

    public QuestionService(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    public List<Question> getQuestionList(){
       return questionDAO.getQuestionList();
   }

    public void loadQuestionList() {
        try {
            questionDAO.loadObjectList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setResource(Resource resource){
        questionDAO.setResource(resource);
    }

    public void print() {
        for (Question question : getQuestionList()) {
            StringBuilder builder = new StringBuilder(question.getValue());
            int i = 1;
            for (Answer answer : question.getAnswerList()) {
                builder.append("\n").append(i).append(". ").append(answer.getValue());
                i += 1;
            }
            System.out.println(builder.toString());
        }
    }
}
