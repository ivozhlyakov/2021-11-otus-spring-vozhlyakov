package ru.ivozhlyakov.rest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ivozhlyakov.rest.dto.Book;
import ru.ivozhlyakov.rest.dto.Embedded;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger log = LoggerFactory.getLogger( BookServiceImpl.class );

    private RestTemplate template = new RestTemplate();

    @Override
    @Cacheable("book_by_id")
    public Book getBookById(long id) {
        log.info("Request");
        return template.getForEntity(URI.create("http://localhost:8080/books/"+id), Book.class).getBody();
    }

    @Override
    public List<Book> getAllBooks() {

        List<Object> objects = (List<Object>) template.getForEntity(URI.create("http://localhost:8080/books/"), Embedded.class)
                .getBody()
                .get_embedded()
                .get("books");

        ObjectMapper mapper = new ObjectMapper();
        return objects.stream()
                .map(object -> mapper.convertValue(object, Book.class))
                .collect(Collectors.toList());
    }
}
