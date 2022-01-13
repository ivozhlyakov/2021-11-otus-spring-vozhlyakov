package ru.ivozhlyakov.libraryorm.shell;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.ivozhlyakov.libraryorm.models.Comment;
import ru.ivozhlyakov.libraryorm.service.CommentServiceImpl;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class CommentCommand {

    private final CommentServiceImpl commentServiceImpl;

    @ShellMethod(value = "Comment list", key = {"comment-list", "comments", "c"})
    public List<Comment> list() {
        return commentServiceImpl.findAll();
    }

    @ShellMethod(value = "Add comment", key = {"add-comment", "ac"})
    public void add(Long bookId, String comment) {
        commentServiceImpl.createComment(bookId, comment);
    }

    @ShellMethod(value = "Update comment", key = {"update-comment", "uc"})
    public void update(Long id, String comment) {
        commentServiceImpl.updateComment(id, comment);
    }

    @ShellMethod(value = "Delete comment", key = {"delete-comment", "dc"})
    public void delete(long id) {
        commentServiceImpl.deleteById(id);
    }

}
