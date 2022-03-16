package ru.ivozhlyakov.rest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ivozhlyakov.rest.dto.Book;
import ru.ivozhlyakov.rest.dto.Embedded;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger log = LoggerFactory.getLogger( BookServiceImpl.class );

    private RestTemplate template = new RestTemplate();

    @Override
    @HystrixCommand(commandProperties= {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="7000")
    }
    )
    public Book getBookById(long id) {
        log.info("Request");
        sleepRandomly();
        Book book;
        try {
            book = template.getForEntity(URI.create("http://localhost:8080/books/"+id), Book.class).getBody();
        } catch (Exception e){
            book = buildFallbackBook();
        }
        return book;
    }

    @HystrixCommand(fallbackMethod="buildFallbackListBook")
    @Override
    public List<Book> getAllBooks() {
        sleepRandomly();
        List<Object> objects = (List<Object>) template.getForEntity(URI.create("http://localhost:8080/books/"), Embedded.class)
                .getBody()
                .get_embedded()
                .get("books");

        ObjectMapper mapper = new ObjectMapper();
        return objects.stream()
                .map(object -> mapper.convertValue(object, Book.class))
                .collect(Collectors.toList());
    }

    private void sleepRandomly() {
        Random rand = new Random();
        int randomNum = rand.nextInt(3) + 1;
        if(randomNum == 3) {
            System.out.println("It is a chance for demonstrating Hystrix action");
            try {
                System.out.println("Start sleeping...." + System.currentTimeMillis());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Hystrix thread interupted...." + System.currentTimeMillis());
                e.printStackTrace();
            }
        }
    }

    public List<Book> buildFallbackListBook() {
        return Collections.singletonList(buildFallbackBook());
    };

    public Book buildFallbackBook() {
        return Book.builder()
                .name("N/A")
                .build();
    };
}

