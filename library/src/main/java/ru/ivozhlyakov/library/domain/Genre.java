package ru.ivozhlyakov.library.domain;

import lombok.Data;

@Data
public class Genre {
    private Long id;
    private String name;

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre(String name) {
        this.name = name;
    }

    public Genre() {
    }
}
