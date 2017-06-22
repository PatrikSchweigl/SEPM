package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.general.Comment;
import at.qe.sepm.asn_app.services.CommentService;
import at.qe.sepm.asn_app.ui.beans.FileBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 02.05.2017
 */

@Component
//@Scope("view")
@Scope("view")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private FileBean fileBean;
    private Comment message;    // TODO change the attribute name from message to comment. It's unnecessarily confusing.

    public Comment getMessage() {
        return message;
    }

    public Collection<Comment> getAllCommentsByPicture(String pictureName) {
        return commentService.getAllCommentsByPicture(pictureName);
    }

    @PostConstruct
    public void init() {
        message = new Comment();
    }

    @PostConstruct
    public void initList() {
        setComments(commentService.getAllMessages());
    }

    public void setComments(Collection<Comment> messages) {
    }

    public void setMessage(Comment message) {
        this.message = message;
        doReloadMessage();
    }
    public void setMessage2(Comment message) {
        this.message = message;
    }


    public void doReloadMessage() {
        message = commentService.loadMessage(message.getId());
    }

    public Comment doSaveMessage() {
        if (fileBean.getSelectedPicture() != null) {
            message.setPictureName(fileBean.getSelectedPicture());
        }
        message = commentService.saveMessage(message);
        Comment messageReturn = message;
        init();
        initList();
        return messageReturn;
    }

    public void doDeleteMessage(Comment message) {
        commentService.deleteMessage(message);
        initList();
    }
}
