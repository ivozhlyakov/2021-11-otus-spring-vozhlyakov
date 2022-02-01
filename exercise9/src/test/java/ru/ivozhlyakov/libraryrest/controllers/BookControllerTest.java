package ru.ivozhlyakov.libraryrest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.ivozhlyakov.exercise9.controllers.BookController;
import ru.ivozhlyakov.exercise9.models.Author;
import ru.ivozhlyakov.exercise9.models.Book;
import ru.ivozhlyakov.exercise9.models.Genre;
import ru.ivozhlyakov.exercise9.service.BookServiceImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Тестируем BookController")
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    BookController bookController;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    BookServiceImpl bookService;

    @Test
    @DisplayName("вернет все книги")
    void bookList() throws Exception {
        List<Book> books = Arrays.asList(
                Book.builder()
                        .id(1L)
                        .name("book1")
                        .authors(Collections.singletonList(new Author(1L, "author1")))
                        .genres(Collections.singletonList(new Genre(1L, "genre1")))
                        .build()
                ,Book.builder()
                        .id(2L)
                        .name("book2")
                        .authors(Collections.singletonList(new Author(2L, "author2")))
                        .genres(Collections.singletonList(new Genre(2L, "genre2")))
                        .build()
        );
        given(bookService.findAll()).willReturn(books);
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(books)));
    }

    @Test
    @DisplayName("вернет выбранную книгу")
    void bookById() throws Exception {
        Book book = Book.builder()
                        .id(1L)
                        .name("book1")
                        .authors(Collections.singletonList(new Author(1L, "author1")))
                        .genres(Collections.singletonList(new Genre(1L, "genre1")))
                        .build();

        given(bookService.findById(1L)).willReturn(Optional.of(book));
        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(Optional.of(book))));
    }

    @Test
    @DisplayName("добавит книгу")
    void addBook() throws Exception {
        Book book = new Book("book"
                , Collections.singletonList(new Author("author"))
                , Collections.singletonList(new Genre("genre"))
        );
        mockMvc.perform(post("/books")
                .param("name", "book")
                .param("author", "author")
                .param("genre", "genre")
                )
                .andExpect(status().isOk());
        verify(bookService).save(book);
    }

    @Test
    @DisplayName("изменит книгу")
    void updateBook() throws Exception {
        Book book = new Book(1L
                ,"book"
                , Collections.singletonList(new Author("author"))
                , Collections.singletonList(new Genre("genre"))
        );
        mockMvc.perform(put("/books/1")
                .param("name", "book")
                .param("author", "author")
                .param("genre", "genre")
        )
                .andExpect(status().isOk());
        verify(bookService).save(book);
    }

    @Test
    @DisplayName("удалит книгу")
    void deleteById() throws Exception {
        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isOk());
        verify(bookService, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("изменит название книги")
    void updateBookNameById() throws Exception {
        mockMvc.perform(patch("/books/2/name")
                .param("name", "newBook")
        )
                .andExpect(status().isOk());

        verify(bookService).updateNameById(2L, "newBook");
    }
}