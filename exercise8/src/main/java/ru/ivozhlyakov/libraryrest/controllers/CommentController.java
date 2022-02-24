package ru.ivozhlyakov.libraryrest.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ivozhlyakov.libraryrest.models.Comment;
import ru.ivozhlyakov.libraryrest.service.CommentService;
import ru.ivozhlyakov.libraryrest.service.CommentServiceImpl;

import java.util.List;

@AllArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comments")
    public List<Comment> list() {
        return commentService.findAll();
    }

    @PostMapping("/comments")
    public void add(@RequestParam(name = "bookId") Long bookId
            ,@RequestParam(name = "comment") String comment) {
        commentService.createComment(bookId, comment);
    }

    @PatchMapping("/comments/{id}/comment")
    public void update(@PathVariable("id") Long id
            ,@RequestParam(name = "comment") String comment) {
        commentService.updateComment(id, comment);
    }

    @DeleteMapping("/comments/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        commentService.deleteById(id);
    }

}
