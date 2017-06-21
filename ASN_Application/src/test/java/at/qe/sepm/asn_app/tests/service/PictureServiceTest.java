package at.qe.sepm.asn_app.tests.service;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.general.Comment;
import at.qe.sepm.asn_app.models.nursery.Picture;
import at.qe.sepm.asn_app.services.PictureService;
import at.qe.sepm.asn_app.services.UserService;
import at.qe.sepm.asn_app.tests.initialize.InitializePicture;
import at.qe.sepm.asn_app.tests.initialize.InitializeUserData;
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

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 14.06.17 12:12 CEST.
 */
@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PictureServiceTest {

    @Autowired
    private PictureService pictureService;
    @Autowired
    private UserService userService;
    private UserData userData;
    private Picture picture;
    private Calendar calendar;


    @Before
    public void initialize() {
        calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("Europe/Vienna"));
        userData = InitializeUserData.initialize1();

        // Set attributes
        picture = InitializePicture.initialize1();
        picture.setPublisher(userData);
    }


    @DirtiesContext
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void test1() {
        // Save the userData in the database.
        userData = userService.saveUser(userData);

        // Save the picture in the database.
        picture = pictureService.savePicture(picture);

        // Check if the values have changed since the picture was saved.
        Picture other = pictureService.loadPicture(picture.getId());
        assertTrue(picture.equals(other));

        // Delete the picture again.
        pictureService.deletePicture(picture);
        other = pictureService.loadPicture(picture.getId());
        assertFalse(picture.equals(other));
        assertNull(other);

        // Delete the userData again.
        userService.deleteUser(userData);
    }


    @Test
    public void testSetterGetter() {
        // Initialize attributes
        Set<Comment> comments = new HashSet<>();
        calendar.clear();
        calendar.set(2014, Calendar.NOVEMBER, 19, 17, 53);
        Date date = calendar.getTime();
        String title = "PictureTitle";
        String url = "PictureUrl";

        // Set attributes
        picture = new Picture();
        picture.setComment(comments);
        picture.setDate(date);
        picture.setPublisher(userData);
        picture.setTitle(title);
        picture.setUrl(url);

        // Compare all attributes with getters.
        assertEquals(comments, picture.getComment());
        assertEquals(date, picture.getDate());
        assertEquals(userData, picture.getPublisher());
        assertEquals(title, picture.getTitle());
        assertEquals(url, picture.getUrl());
    }


    @Test
    public void testFurtherMethods() {
        // Test toString()
        assertNotNull(picture.toString());
        assertNotEquals("", picture.toString());

        // Test isNew()
        picture = new Picture();
        assertNull(picture.getUrl());
        picture.setUrl("");
        assertTrue(picture.isNew());
        picture.setUrl("Url");
        assertFalse(picture.isNew());

    }


    @After
    public void cleanUp() {
        calendar = null;
        picture = null;
        userData = null;
    }
}
