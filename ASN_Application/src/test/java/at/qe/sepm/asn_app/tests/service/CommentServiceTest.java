package at.qe.sepm.asn_app.tests.service;

import at.qe.sepm.asn_app.models.general.Comment;
import at.qe.sepm.asn_app.services.CommentService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

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


    /**
     * Initialize a comment with some default values.
     */
    @Before
    public void initialize() {
        comment = new Comment();
        comment.setComment("I am a comment.");
        comment.setDate(new Date());
        comment.setPictureName("PictureName1");
        comment.setUsername("Username1");
    }


    /**
     * Save a comment in the database and check if values have changed.
     * This test only succeeds if the comment stays the same.
     */
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


    /**
     * This test is just for completeness to make sure
     * that everything works and is covered 100%.
     */
    @Test
    public void testSetterGetter() {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.clear();
        calendar.set(2017, Calendar.JULY, 11, 16, 47);

        // Initialize attributes
        String content = "Content";
        Date date = calendar.getTime();
        String pictureName = "PictureName";
        String username = "UserName";

        // Set attributes
        comment.setComment(content);
        comment.setDate(date);
        comment.setPictureName(pictureName);
        comment.setUsername(username);

        // Compare all attributes with getters.
        assertEquals(content, comment.getComment());
        assertEquals(date, comment.getDate());
        assertEquals(pictureName, comment.getPictureName());
        assertEquals(username, comment.getUsername());
    }


    /**
     * Test all methods of comment that haven't been tested yet.
     */
    @Test
    public void testFurtherMethods() {
        // Print all information with toString();
        assertNotEquals("", comment.toString());
        System.out.println(comment.toString());

        // Test isNew();
        assertFalse(comment.isNew());
    }


    @After
    public void cleanUp() {
        comment = null;
    }
}
