package ru.ivozhlyakov.library.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.ivozhlyakov.library.domain.Author;
import ru.ivozhlyakov.library.mapper.AuthorMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    private NamedParameterJdbcOperations namedParameterJdbcOperations;
    private AuthorMapper authorMapper;

    public AuthorDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations, AuthorMapper authorMapper) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.authorMapper = authorMapper;
    }

    @Override
    public int count() {
        return namedParameterJdbcOperations.getJdbcOperations().queryForObject("select count(*) from author", Integer.class);
    }

    @Override
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

    @Override
    public Author getById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        try {

            return namedParameterJdbcOperations.queryForObject(
                    "select * from author where id = :id", params, authorMapper
            );
        }catch (Exception e){
            return new Author();
        }
    }

}
