package ru.ivozhlyakov.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ivozhlyakov.library.dao.BookDao;
import ru.ivozhlyakov.library.domain.Book;
import ru.ivozhlyakov.library.domain.Genre;

import java.util.List;

@Service
public class BookService {

    private BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public String getBookNameByID(Long id) {
        return bookDao.getById(id).getName();
    }

    public List<Book> getAll() {
        return bookDao.getAll();
    }

    public Long insert(String bookName) {
        return bookDao.insert(new Book(bookName));
    }

    public void deleteByID(Long id) {
        bookDao.deleteById(id);
    }
}
