package ru.ivozhlyakov.library.domain;

import lombok.Data;

@Data
public class Author {
    private Long id;
    private String brief;

    public Author(Long id, String brief) {
        this.id = id;
        this.brief = brief;
    }

    public Author(String brief) {
        this.brief = brief;
    }

    public Author() {
    }
}
