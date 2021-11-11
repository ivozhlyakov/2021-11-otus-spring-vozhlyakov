package ru.ivozhlyakov.springStudTest.service;

import ru.ivozhlyakov.springStudTest.dao.QuestionDAO;
import ru.ivozhlyakov.springStudTest.domain.Question;

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


    public void loadQuestionList(InputStream stream) {
        questionDAO.loadObjectList(stream);
    }
}
