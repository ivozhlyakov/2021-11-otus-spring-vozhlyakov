package ru.ivozhlyakov.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ivozhlyakov.library.dao.BookInfoDao;
import ru.ivozhlyakov.library.domain.Author;
import ru.ivozhlyakov.library.domain.Book;
import ru.ivozhlyakov.library.domain.BookInfo;
import ru.ivozhlyakov.library.domain.Genre;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookInfoService {

    @Autowired
    private BookInfoDao bookInfoDao;

    @Autowired
    private AuthorService authorService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private BookService bookService;

    public BookInfoService(BookInfoDao bookInfoDao) {
        this.bookInfoDao = bookInfoDao;
    }

    private String formatOut(BookInfo bookInfo) {
        return "\""+bookInfo.getBookName() +"\" - "
                + bookInfo.getAuthor() + ", "
                + bookInfo.getGenre();
    }

    public String getBookInfoByID(Long id) {
        return formatOut(
                bookInfoDao.getBookInfoByID(id)
        );
    }

    public Set<String> getAllBookWithInfo() {
        List<BookInfo> bookInfos = bookInfoDao.getAllBookWithInfo();
        Set<String> set = new HashSet<>(bookInfos.size());
        for (BookInfo bookInfo : bookInfos){
            set.add(formatOut(bookInfo));
        }
        return set;
    }

    public Long insert(String bookName, String authorName, String genreName) {
        Book book = new Book(
                bookService.insert(bookName)
                , bookName
        );
        Author author = authorService.getByName(authorName);
        if (author.getId() == null) {
            author = new Author(
                    authorService.insert(authorName)
                    , authorName
            );
        }
        Genre genre = genreService.getByName(genreName);
        if (genre.getId() == null) {
            genre = new Genre(
                    genreService.insert(genreName)
                    , genreName
            );
        }

        return bookInfoDao.insert(book, author, genre);
    }
}
