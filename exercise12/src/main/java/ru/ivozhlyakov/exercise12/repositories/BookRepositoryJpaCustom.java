package ru.ivozhlyakov.exercise12.repositories;


import ru.ivozhlyakov.exercise12.domain.Book;

import java.util.List;

public interface BookRepositoryJpaCustom {

    List<Book> findAll();
}
