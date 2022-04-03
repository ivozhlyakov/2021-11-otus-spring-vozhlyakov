package ru.ivozhlyakov.rest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "books")
public class Book {

    public Book(String name, List<Author> authors, List<Genre> genres) {
        this.name = name;
        this.authors = authors;
        this.genres = genres;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(targetEntity = Author.class
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE}
            , fetch = FetchType.LAZY
    )
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))

    private List<Author> authors;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(targetEntity = Genre.class
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE}
            , fetch = FetchType.LAZY)
    @JoinTable(name = "book_genre", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

}
