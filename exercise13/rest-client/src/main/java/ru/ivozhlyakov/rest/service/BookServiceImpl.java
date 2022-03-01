package ru.ivozhlyakov.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import ru.ivozhlyakov.rest.dto.Book;
import ru.ivozhlyakov.rest.dto.CollectionBook;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.Collections;
import java.util.List;

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
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RequestEntity<Book> requestEntity = new RequestEntity<Book>(
                new Book()
                ,headers
                ,HttpMethod.GET
                ,URI.create("http://localhost:8080/books/")
        );
        ResponseEntity<CollectionBook> responseEntity = template.exchange(
                requestEntity,
                new ParameterizedTypeReference<CollectionBook>() {}
        );

        return (List<Book>) responseEntity.getBody().get_embedded().get("books");
    }
}
