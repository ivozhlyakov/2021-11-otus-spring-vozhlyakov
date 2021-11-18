package ru.ivozhlyakov.springStudTest;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import ru.ivozhlyakov.springStudTest.domain.Question;
import ru.ivozhlyakov.springStudTest.service.QuestionService;

import java.io.IOException;

public class Main {

	public static void main(String[] args)
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        QuestionService questionService = context.getBean(QuestionService.class);

        questionService.setResource(context.getResource("classpath:simpleTesForTheVerbTO_BE.csv"));
        questionService.loadQuestionList();
        questionService.print();

        context.close();
	}

}
