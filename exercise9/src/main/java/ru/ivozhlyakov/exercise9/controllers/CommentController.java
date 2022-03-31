package ru.ivozhlyakov.exercise9.controllers;


import org.springframework.web.bind.annotation.*;
import ru.ivozhlyakov.exercise9.service.CommentServiceImpl;
import ru.ivozhlyakov.exercise9.models.Comment;

import java.util.List;

@RestController
public class CommentController {

    private final CommentServiceImpl commentServiceImpl;

    public CommentController(CommentServiceImpl commentServiceImpl) {
        this.commentServiceImpl = commentServiceImpl;
    }

    @GetMapping("/comments")
    public List<Comment> list() {
        return commentServiceImpl.findAll();
    }

    @PostMapping("/comments")
    public void add(@RequestParam(name = "bookId") Long bookId
            ,@RequestParam(name = "comment") String comment) {
        commentServiceImpl.createComment(bookId, comment);
    }

    @PatchMapping("/comments/{id}/comment")
    public void update(@PathVariable("id") Long id
            ,@RequestParam(name = "comment") String comment) {
        commentServiceImpl.updateComment(id, comment);
    }

    @DeleteMapping("/comments/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        commentServiceImpl.deleteById(id);
    }

}
