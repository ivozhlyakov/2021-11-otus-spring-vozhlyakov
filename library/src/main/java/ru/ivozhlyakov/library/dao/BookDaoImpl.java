package ru.ivozhlyakov.library.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.ivozhlyakov.library.domain.Author;
import ru.ivozhlyakov.library.domain.Book;
import ru.ivozhlyakov.library.domain.Genre;
import ru.ivozhlyakov.library.mapper.BookMapper;

import java.util.*;


@Repository
public class BookDaoImpl implements BookDao{

    private NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final BookMapper bookMapper;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations, BookMapper bookMapper, AuthorDao authorDao, GenreDao genreDao) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.bookMapper = bookMapper;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public int count(){
        return namedParameterJdbcOperations.getJdbcOperations().queryForObject("select count(*) from book", Integer.class);
    }

    @Override
    public Long insert(Book book) {
        Author author = Optional.ofNullable(book.getAuthor()).orElse(new Author());
        Genre genre = Optional.ofNullable(book.getGenre()).orElse(new Genre());

        Long author_id = Optional.ofNullable(author.getId()).orElse(authorDao.insert(author));
        Long genre_id = Optional.ofNullable(genre.getId()).orElse(genreDao.insert(genre));

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", book.getName());
        params.addValue("author_id", author_id);
        params.addValue("genre_id", genre_id);
        KeyHolder kh = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update(
                "insert into book (`name`, `author_id`, `genre_id`)  values (:name, :author_id, :genre_id)"
                , params
                , kh
        );
        return kh.getKey().longValue();
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query(
                "select " +
                        "book.id as book_id" +
                        " ,book.name as book_name " +
                        " ,author.id as author_id " +
                        " ,author.brief as author_brief " +
                        " ,genre.id as genre_id " +
                        " ,genre.name as genre_name " +
                        "from book " +
                        "join author on author.id = book.author_id " +
                        "join genre on genre.id = book.genre_id ", bookMapper);
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from book where id = :id", params
        );
    }

    @Override
    public void updateBookName(Book book) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("id", book.getId());
        params.put("name", book.getName());
        namedParameterJdbcOperations.update(
                "update book set name = :name where id = :id",
                params
        );
    }

    @Override
    public Book getBookByID(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select " +
                        "book.id as book_id"+
                        " ,book.name as book_name " +
                        " ,author.id as author_id " +
                        " ,author.brief as author_brief " +
                        " ,genre.id as genre_id " +
                        " ,genre.name as genre_name " +
                        "from book " +
                        "join author on author.id = book.author_id " +
                        "join genre on genre.id = book.genre_id " +
                        "where book.id = :id"
                , params
                , bookMapper
        );
    }


}
