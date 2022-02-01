package ru.ivozhlyakov.libraryrest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.ivozhlyakov.exercise9.controllers.GenreController;
import ru.ivozhlyakov.exercise9.models.Genre;
import ru.ivozhlyakov.exercise9.service.GenreServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Тестирование GenreController")
@SpringBootTest
@AutoConfigureMockMvc
class GenreControllerTest {

    @Autowired
    GenreController genreController;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    GenreServiceImpl genreService;

    @DisplayName("вернет все жанры")
    @Test
    void shouldReturnAllGenres() throws Exception {
        List<Genre> genres = Arrays.asList(
                new Genre(1L, "one")
                ,new Genre(2L, "two")
        );
        when(genreService.findAll())
                .thenReturn(genres);

        mockMvc.perform(get("/genres"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(genres)));

    }
}