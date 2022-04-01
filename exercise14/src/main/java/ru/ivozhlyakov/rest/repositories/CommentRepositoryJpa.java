package ru.ivozhlyakov.rest.repositories;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ivozhlyakov.rest.domain.Comment;

@RepositoryRestResource(path = "comments")
public interface CommentRepositoryJpa extends PagingAndSortingRepository<Comment, Long> {

}
