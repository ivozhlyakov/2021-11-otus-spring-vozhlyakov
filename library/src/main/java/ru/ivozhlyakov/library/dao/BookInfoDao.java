package ru.ivozhlyakov.library.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.ivozhlyakov.library.domain.Author;
import ru.ivozhlyakov.library.domain.Book;
import ru.ivozhlyakov.library.domain.BookInfo;
import ru.ivozhlyakov.library.domain.Genre;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class BookInfoDao {

    private JdbcOperations jdbcOperations;
    private NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookInfoDao(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.jdbcOperations = namedParameterJdbcOperations.getJdbcOperations();
    }

    public Long insert(Book book, Author author, Genre genre) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("bookid", book.getId());
        parameterSource.addValue("authorid", author.getId());
        parameterSource.addValue("genreid", genre.getId());

        namedParameterJdbcOperations.update(
                "insert into booklinkinfo(bookid, authorid, genreid)" +
                        " values(:bookid, :authorid, :genreid)"
                , parameterSource
        );

        return book.getId();
    }

    public BookInfo getBookInfoByID(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select" +
                        " book.name as bookName " +
                        " ,author.brief as author " +
                        " ,genre.name as genre " +
                        "from booklinkinfo " +
                        "join book on book.id = booklinkinfo.bookid " +
                        "join author on author.id = booklinkinfo.authorid " +
                        "join genre on genre.id = booklinkinfo.genreid " +
                        "where booklinkinfo.bookid = :id"
                , params
                , new BookInfoMapper()
        );
    }

    public List<BookInfo> getAllBookWithInfo() {
        return jdbcOperations.query(
                "select" +
                        " book.name as bookName " +
                        " ,author.brief as author " +
                        " ,genre.name as genre " +
                        "from booklinkinfo " +
                        "join book on book.id = booklinkinfo.bookid " +
                        "join author on author.id = booklinkinfo.authorid " +
                        "join genre on genre.id = booklinkinfo.genreid "
                , new BookInfoMapper()
        );
    }

    private static class BookInfoMapper implements RowMapper<BookInfo> {
        @Override
        public BookInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new BookInfo(
                    rs.getString("bookName")
                    ,rs.getString("author")
                    ,rs.getString("genre")
            );
        }
    }

}
