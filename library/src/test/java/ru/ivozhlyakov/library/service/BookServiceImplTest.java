package ru.ivozhlyakov.library.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import ru.ivozhlyakov.library.dao.TestDaoConfiguration;
import ru.ivozhlyakov.library.domain.Author;
import ru.ivozhlyakov.library.domain.Book;
import ru.ivozhlyakov.library.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Сервис для работы с полной информацией по книге")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Import(TestDaoConfiguration.class)
class BookServiceImplTest {

    @Autowired
    private BookServiceImpl service;

    private Book createBook(String name, String authorFio, String genreName) {
        return Book.builder()
                .name(name)
                .author(Author.builder()
                        .brief(authorFio)
                        .build())
                .genre(Genre.builder()
                        .name(genreName)
                        .build())
                .build();
    }

    @DisplayName("возвращает информацию о книге по заданному идентификатору")
    @Test
    void getBookInfoByID() {
        assertThat(service.getById(1L)).isNotNull();
    }

    @DisplayName("возвращает список книг")
    @Test
    void getAllBookWithInfo() {
        assertThat(service.getAll().size() > 0).isEqualTo(Boolean.TRUE);
    }

    @DisplayName("добавляет книгу с информацией о ней")
    @Test
    void insert() {
        Book book = createBook("BookName", "AuthorNAme", "GenreName");
        Long id = service.insert(book);
        assertThat(id.compareTo(0L)).isEqualTo(1);
    }
}