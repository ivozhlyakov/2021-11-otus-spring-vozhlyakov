package ru.ivozhlyakov.library.service;

import org.springframework.stereotype.Service;
import ru.ivozhlyakov.library.dao.BookDaoImpl;
import ru.ivozhlyakov.library.domain.Book;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private BookDaoImpl bookDaoImpl;

    public BookServiceImpl(BookDaoImpl bookDaoImpl) {
        this.bookDaoImpl = bookDaoImpl;
    }

    @Override
    public List<Book> getAll() {
        return bookDaoImpl.getAll();
    }

    @Override
    public Long insert(Book book) {
        return bookDaoImpl.insert(book);
    }

    @Override
    public Book getById(Long id) {
        return bookDaoImpl.getBookByID(id);
    }

    @Override
    public void deleteByID(Long id) {
        bookDaoImpl.deleteById(id);
    }

    @Override
    public void updateBookNameByID(Book book){
        bookDaoImpl.updateBookName(book);}
}
