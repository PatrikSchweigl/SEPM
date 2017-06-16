package at.qe.sepm.asn_app.tests.service;

import at.qe.sepm.asn_app.models.general.Comment;
import at.qe.sepm.asn_app.services.CommentService;
import at.qe.sepm.asn_app.tests.controller.ContextMocker;
import at.qe.sepm.asn_app.ui.controllers.CommentController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.faces.context.FacesContext;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 14.06.17 11:54 CEST.
 */
@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CommentServiceTest {
    @Autowired
    private CommentService commentService;
    private Comment comment;


    @Before
    public void initialize() {
        comment = new Comment();
        comment.setComment("I am a comment.");
        comment.setDate(new Date());
        comment.setPictureName("PictureName1");
        comment.setUsername("Username1");
    }


    @DirtiesContext
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void testSingleComment() {
        // Save a comment in the database.
        comment = commentService.saveMessage(comment);

        // Check if the values have changed since the comment was saved.
        Comment other = commentService.loadMessage(comment.getId());
        assertTrue(comment.equals(other));

        // Delete the comment again.
        commentService.deleteMessage(comment);
        other = commentService.loadMessage(comment.getId());
        assertFalse(comment.equals(other));
        assertNull(other);
    }


    public void testMultipleComments() {

    }


    @After
    public void cleanUp() {
        comment = null;
    }
}
