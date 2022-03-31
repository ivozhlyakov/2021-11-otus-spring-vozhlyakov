package ru.ivozhlyakov.exercise10.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ivozhlyakov.exercise10.service.CommentService;
import ru.ivozhlyakov.exercise10.service.CommentServiceImpl;
import ru.ivozhlyakov.exercise10.models.Comment;

import java.util.List;

@RestController
@AllArgsConstructor
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

    @DeleteMapping("/comments/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        commentService.deleteById(id);
    }

}
