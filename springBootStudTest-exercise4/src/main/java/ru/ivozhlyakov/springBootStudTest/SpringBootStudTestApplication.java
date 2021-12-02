package ru.ivozhlyakov.springBootStudTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.ivozhlyakov.springBootStudTest.service.QuestionService;

@SpringBootApplication
public class SpringBootStudTestApplication {

	public static void main(String[] args) {
	    ApplicationContext context = SpringApplication.run(SpringBootStudTestApplication.class, args);
        QuestionService questionService = context.getBean(QuestionService.class);

        questionService.test();

	}

}
