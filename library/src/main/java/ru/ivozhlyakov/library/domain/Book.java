package ru.ivozhlyakov.library.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Book {
    private Long id;
    private String name;
    private Author author;
    private Genre genre;


}
