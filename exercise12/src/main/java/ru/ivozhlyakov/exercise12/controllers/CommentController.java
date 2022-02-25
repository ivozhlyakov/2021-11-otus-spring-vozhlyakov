package ru.ivozhlyakov.exercise12.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ivozhlyakov.exercise12.domain.Comment;
import ru.ivozhlyakov.exercise12.service.CommentService;

@RestController
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/api/comments")
    public Iterable<Comment> list() {
        return commentService.findAll();
    }

    @PostMapping("/api/comments")
    public void add(@RequestParam(name = "bookId") Long bookId
            ,@RequestParam(name = "comment") String comment) {
        commentService.createComment(bookId, comment);
    }

    @PatchMapping("/api/comments/{id}/comment")
    public void update(@PathVariable("id") Long id
            ,@RequestParam(name = "comment") String comment) {
        commentService.updateComment(id, comment);
    }

    @DeleteMapping("/api/comments/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        commentService.deleteById(id);
    }

}
