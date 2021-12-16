package ru.ivozhlyakov.library.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.ivozhlyakov.library.domain.Author;
import ru.ivozhlyakov.library.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class AuthorDao {

    private final JdbcOperations jdbc;
    private NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDao(NamedParameterJdbcOperations namedParameterJdbcOperations)
    {
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    public Long insert(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("brief", author.getBrief());

        KeyHolder kh = new GeneratedKeyHolder();

        namedParameterJdbcOperations.update(
                "insert into author (`brief`) values (:brief)"
                ,params
                ,kh
        );
        return Objects.requireNonNull(kh.getKey()).longValue();
    }

    public Author getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select * from author where id = :id", params, new AuthorMapper()
        );
    }

    public Author getByName(String brief) {
        Map<String, Object> params = Collections.singletonMap("brief", brief);
        try {

            return namedParameterJdbcOperations.queryForObject(
                    "select * from author where upper(brief) = upper(:brief)", params, new AuthorMapper()
            );
        }catch (Exception e){
            return new Author();
        }
    }

    public List<Author> getAll() {
        return jdbc.query("select * from author", new AuthorMapper());
    }

    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from author where id = :id", params
        );
    }

    private static class AuthorMapper implements RowMapper<Author>{

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author(rs.getLong("id"), rs.getString("brief"));
        }
    }
}