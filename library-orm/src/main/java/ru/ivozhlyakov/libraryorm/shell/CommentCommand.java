package ru.ivozhlyakov.libraryorm.shell;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.ivozhlyakov.libraryorm.models.Comment;
import ru.ivozhlyakov.libraryorm.service.CommentService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class CommentCommand {

    private final CommentService commentService;

    @ShellMethod(value = "Comment list", key = {"comment-list", "comments", "c"})
    public List<Comment> list() {
        return commentService.findAll();
    }

    @ShellMethod(value = "Add comment", key = {"add-comment", "ac"})
    public void add(Long bookId, String comment) {
        commentService.createComment(bookId, comment);
    }

    @ShellMethod(value = "Delete comment", key = {"delete-comment", "dc"})
    public void delete(long id) {
        commentService.deleteById(id);
    }

}
