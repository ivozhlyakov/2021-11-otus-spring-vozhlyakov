package ru.ivozhlyakov.library.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.ivozhlyakov.library.dao.BookInfoDao;
import ru.ivozhlyakov.library.domain.BookInfo;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Сервис для работы с полной информацией по книге")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Import(BookInfoDao.class)
class BookInfoServiceTest {

    @Autowired
    private BookInfoDao dao;

    @Autowired
    private BookInfoService service;

    @DisplayName("возвращает информацию о книге по заданному идентификатору")
    @Test
    void getBookInfoByID() {
        assertThat(service.getBookInfoByID(1L)).isNotNull();
    }

    @DisplayName("возвращает список книг")
    @Test
    void getAllBookWithInfo() {
        assertThat(service.getAllBookWithInfo().size() > 0).isEqualTo(Boolean.TRUE);
    }

    @DisplayName("добавляет книгу с информацией о ней")
    @Test
    void insert() {
        Long id = service.insert("BookName", "AuthorNAme", "GenreName");
        assertThat(id.compareTo(0L)).isEqualTo(1);
    }
}