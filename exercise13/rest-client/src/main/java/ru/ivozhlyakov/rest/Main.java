package ru.ivozhlyakov.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.retry.annotation.EnableRetry;
import ru.ivozhlyakov.rest.service.BookService;

@EnableCaching
@EnableRetry
@SpringBootApplication
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Main.class, args);

        BookService service = ctx.getBean(BookService.class);

        log.info("-------------------------------- ");
        long l = 1L;
        do {
            log.info(service.getBookById(l).getName());
            l++;
        } while (l < 4);
        log.info("-------------------------------- ");
        l = 1L;
        do {
            log.info(service.getBookById(l).getName());
            l++;
        } while (l < 4);

        log.info("-------------------------------- ");

        log.info(service.getAllBooks().toString());

    }
}
