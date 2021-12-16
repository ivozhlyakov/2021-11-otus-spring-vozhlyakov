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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDao {

    private final JdbcOperations jdbc;
    private NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDao(NamedParameterJdbcOperations namedParameterJdbcOperations)
    {
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    public int count(){
        return jdbc.queryForObject("select count(*) from book", Integer.class);
    }

    public Long insert(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", book.getName());
        KeyHolder kh = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update(
                "insert into book (`name`) values (:name)"
                , params
                , kh
        );
        return kh.getKey().longValue();
    }

    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select * from book where id = :id", params, new BookMapper()
        );
    }

    public List<Book> getAll() {
        return jdbc.query("select * from book", new BookMapper());
    }

    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from book where id = :id", params
        );
    }

    public void updateBookName(Book book) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("id", book.getId());
        params.put("name", book.getName());
        namedParameterJdbcOperations.update(
                "update book set name = :name where id = :id",
                params
        );
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book(rs.getLong("id"), rs.getString("name"));
        }
    }

}
