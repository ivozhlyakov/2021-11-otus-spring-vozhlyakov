package ru.ivozhlyakov.library.domain;

import lombok.Data;

@Data
public class BookInfo {
    private Long id;
    private String bookName;
    private String author;
    private String genre;

    public BookInfo(String bookName, String author, String genre) {
        this.bookName = bookName;
        this.author = author;
        this.genre = genre;
    }
}
