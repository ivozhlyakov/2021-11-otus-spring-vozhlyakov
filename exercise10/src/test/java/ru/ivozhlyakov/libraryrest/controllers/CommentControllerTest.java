package ru.ivozhlyakov.libraryrest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.ivozhlyakov.exercise10.LibraryOrmApplication;
import ru.ivozhlyakov.exercise10.controllers.CommentController;
import ru.ivozhlyakov.exercise10.models.Author;
import ru.ivozhlyakov.exercise10.models.Book;
import ru.ivozhlyakov.exercise10.models.Comment;
import ru.ivozhlyakov.exercise10.models.Genre;
import ru.ivozhlyakov.exercise10.service.CommentServiceImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Тестируем CommentController")
@SpringBootTest(classes = LibraryOrmApplication.class)
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
    @WithMockUser(value = "user")
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
    @WithMockUser(value = "admin", authorities = {"ROLE_ADMIN"})
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
    @WithMockUser(value = "admin", authorities = {"ROLE_USER"})
    @DisplayName("не добавит комментарий пользователю не верной роли")
    void shouldNotAddComment() throws Exception{
        Comment comment = new Comment(1L, "text", Book.builder()
                .id(1L)
                .name("book1")
                .authors(Collections.singletonList(new Author(1L, "author")))
                .genres(Collections.singletonList(new Genre(1L, "genre1")))
                .build()
        );

        mockMvc.perform(post("/comments?bookId=1&comment=text"))
                .andExpect(status().isForbidden());

    }

    @WithMockUser(value = "admin", authorities = {"ROLE_ADMIN"})
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
    @WithMockUser(value = "admin", authorities = {"ROLE_ADMIN"})
    @DisplayName("удалит комментарий")
    void deleteById() throws Exception {
        mockMvc.perform(delete("/comments/1"))
                .andExpect(status().isOk());
        verify(commentService, times(1)).deleteById(1L);
    }

}