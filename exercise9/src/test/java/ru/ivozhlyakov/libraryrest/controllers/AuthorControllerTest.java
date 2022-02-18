package ru.ivozhlyakov.libraryrest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.ivozhlyakov.exercise9.config.SecurityConfig;
import ru.ivozhlyakov.exercise9.controllers.AuthorController;
import ru.ivozhlyakov.exercise9.models.Author;
import ru.ivozhlyakov.exercise9.service.AuthorServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Тестирование AuthorController")
@SpringBootTest(classes = {AuthorController.class, ObjectMapper.class})
@AutoConfigureMockMvc
@EnableWebMvc
class AuthorControllerTest {
    @Autowired
    AuthorController authorController;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    AuthorServiceImpl authorService;

    @WithMockUser(
            username = "admin"
    )
    @DisplayName("вернет всех авторов")
    @Test
    void shouldReturnAllAutors() throws Exception {
        List<Author> authors = Arrays.asList(
                new Author(1L, "one")
                ,new Author(2L, "two")
        );
        when(authorService.findAll())
                .thenReturn(authors);

        mockMvc.perform(
                get("/authors")
        )
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(authors)));

    }
}