package ru.ivozhlyakov.library.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.ivozhlyakov.library.domain.Genre;

public interface GenreDao {
    Long insert(Genre genre);
    Genre getById(Long id);
}
