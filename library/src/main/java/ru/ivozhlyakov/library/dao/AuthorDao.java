package ru.ivozhlyakov.library.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.ivozhlyakov.library.domain.Author;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public interface AuthorDao {
    Long insert(Author author);
    Author getById(Long id);
    int count();

}
