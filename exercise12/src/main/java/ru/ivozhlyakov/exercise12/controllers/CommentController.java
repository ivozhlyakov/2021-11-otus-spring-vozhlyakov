package ru.ivozhlyakov.exercise12.controllers;


import org.springframework.web.bind.annotation.*;
import ru.ivozhlyakov.exercise12.domain.Comment;
import ru.ivozhlyakov.exercise12.service.CommentServiceImpl;

@RestController
public class CommentController {

    private final CommentServiceImpl commentServiceImpl;

    public CommentController(CommentServiceImpl commentServiceImpl) {
        this.commentServiceImpl = commentServiceImpl;
    }

    @GetMapping("/api/comments")
    public Iterable<Comment> list() {
        return commentServiceImpl.findAll();
    }

    @PostMapping("/api/comments")
    public void add(@RequestParam(name = "bookId") Long bookId
            ,@RequestParam(name = "comment") String comment) {
        commentServiceImpl.createComment(bookId, comment);
    }

    @PatchMapping("/api/comments/{id}/comment")
    public void update(@PathVariable("id") Long id
            ,@RequestParam(name = "comment") String comment) {
        commentServiceImpl.updateComment(id, comment);
    }

    @DeleteMapping("/api/comments/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        commentServiceImpl.deleteById(id);
    }

}
