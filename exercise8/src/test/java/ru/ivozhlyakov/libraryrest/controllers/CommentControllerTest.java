package ru.ivozhlyakov.libraryrest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.ivozhlyakov.libraryrest.models.Author;
import ru.ivozhlyakov.libraryrest.models.Book;
import ru.ivozhlyakov.libraryrest.models.Comment;
import ru.ivozhlyakov.libraryrest.models.Genre;
import ru.ivozhlyakov.libraryrest.service.CommentServiceImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Тестируем CommentController")
@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest {

    @Autowired
    CommentController commentController;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    CommentServiceImpl commentService;

    @Test
    @DisplayName("вернет все комментарии")
    void shouldReturnAllComments() throws Exception {
        List<Comment> genres = Arrays.asList(
                new Comment(1L, "one", Book.builder()
                        .id(1L)
                        .name("book1")
                        .authors(Collections.singletonList(new Author(1L, "author")))
                        .genres(Collections.singletonList(new Genre(1L, "genre1")))
                        .build()
                )
                ,new Comment(2L, "one", Book.builder()
                        .id(2L)
                        .name("book2")
                        .authors(Collections.singletonList(new Author(2L, "author")))
                        .genres(Collections.singletonList(new Genre(2L, "genre1")))
                        .build()
                )
        );
        when(commentService.findAll())
                .thenReturn(genres);

        mockMvc.perform(get("/comments"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(genres)));
    }

    @Test
    @DisplayName("добавит комментарий")
    void shouldAddComment() throws Exception{
        Comment comment = new Comment(1L, "text", Book.builder()
                .id(1L)
                .name("book1")
                .authors(Collections.singletonList(new Author(1L, "author")))
                .genres(Collections.singletonList(new Genre(1L, "genre1")))
                .build()
        );

        mockMvc.perform(post("/comments?bookId=1&comment=text"))
                .andExpect(status().isOk());

        verify(commentService).createComment(1L, "text");
    }

    @Test
    @DisplayName("изменит комментарий")
    void update() throws Exception {
        Comment comment = new Comment(4L, "text", Book.builder()
                .id(4L)
                .name("book1")
                .authors(Collections.singletonList(new Author(4L, "author")))
                .genres(Collections.singletonList(new Genre(4L, "genre1")))
                .build()
        );
        given(commentService.findAll()).willReturn(Collections.singletonList(comment));
        given(commentService.save(any())).willAnswer(invocation -> invocation.getArgument(0));

        Comment comment2 = new Comment(4L, "text2222", Book.builder()
                .id(4L)
                .name("book1")
                .authors(Collections.singletonList(new Author(4L, "author")))
                .genres(Collections.singletonList(new Genre(4L, "genre1")))
                .build()
        );
        String expectedResult = mapper.writeValueAsString(comment2);

        mockMvc.perform(patch("/comments/{id}/comment", 4).param("comment", comment2.getComment())
                .content(expectedResult))
                .andExpect(status().isOk())
 ;//               .andExpect(content().json(expectedResult));
    }

    @Test
    @DisplayName("удалит комментарий")
    void deleteById() throws Exception {
        mockMvc.perform(delete("/comments/1"))
                .andExpect(status().isOk());
        verify(commentService, times(1)).deleteById(1L);
    }
}