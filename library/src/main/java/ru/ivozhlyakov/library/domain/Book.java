package ru.ivozhlyakov.library.domain;

import lombok.Data;

@Data
public class Book {
    private Long id;
    private String name;

    public Book(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Book(String name) {
        this.name = name;
    }
}
