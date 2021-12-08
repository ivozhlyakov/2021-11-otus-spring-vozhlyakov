package ru.ivozhlyakov.springStudTest;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.ivozhlyakov.springStudTest.service.QuestionService;

@ComponentScan
@PropertySource(value = "classpath:application.properties")
public class Main {

	public static void main(String[] args)
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        QuestionService questionService = context.getBean(QuestionService.class);

        questionService.test();

        context.close();
	}

}
