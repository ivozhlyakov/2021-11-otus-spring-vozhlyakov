package ru.ivozhlyakov.library.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.ivozhlyakov.library.domain.Book;
import ru.ivozhlyakov.library.domain.Genre;
import ru.ivozhlyakov.library.mapper.GenreMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoImpl implements GenreDao{

    private NamedParameterJdbcOperations namedParameterJdbcOperations;
    private GenreMapper genreMapper;

    public GenreDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations, GenreMapper genreMapper) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.genreMapper = genreMapper;
    }

    @Override
    public Long insert(Genre genre) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", genre.getName());

        KeyHolder kh = new GeneratedKeyHolder();

        namedParameterJdbcOperations.update(
                "insert into genre (`name`) values (:name)"
                ,params
                ,kh
        );
        return kh.getKey().longValue();
    }

    @Override
    public Genre getById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject("select * from genre where id = :id", params, genreMapper);
    }

}
