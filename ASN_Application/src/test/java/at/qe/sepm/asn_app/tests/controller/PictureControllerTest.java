package at.qe.sepm.asn_app.tests.controller;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.general.Comment;
import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.models.nursery.Picture;
import at.qe.sepm.asn_app.services.PictureService;
import at.qe.sepm.asn_app.services.UserService;
import at.qe.sepm.asn_app.ui.controllers.PictureController;
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 11.06.17 16:44 CEST.
 */
@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PictureControllerTest {

    @Autowired
    private PictureController pictureController;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private UserService userService;
    private UserData userData;
    private Picture picture;


    @Before
    public void initialize() {
        Comment comment = new Comment();
        comment.setComment("I am a comment.");
        comment.setDate(new Date());
        comment.setPictureName("PictureName1");
        comment.setUsername("Username1");
        Set<Comment> comments = new HashSet<>();

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.clear();
        calendar.set(2017, 6, 11, 16, 47);
        Date date = calendar.getTime();

        userData = new UserData();
        userData.setBirthday("22/05/1989");
        userData.setEmail("UserDataEmail1@google.com");
        userData.setFirstName("UserDataFirstName1");
        userData.setLastName("UserDataLastName1");
        userData.setImgName("UserDataImgName1");
        userData.setLocation("UserDataLocation1");
        userData.setNotification(true);
        userData.setPassword("passwd");
        userData.setPhoneNumber("0123456789");
        userData.setPostcode("6020");
        userData.setReligion(Religion.CHRISTENTUM);
        userData.setStreetName("UserDataStreetName1");
        userData.setUsername("UserDataUsername1");
        userData.setUserRole(UserRole.PARENT);

        picture = new Picture();
        picture.setComment(comments);
        picture.setDate(date);
        picture.setPublisher(userData);
        picture.setTitle("PictureTitle1");
        picture.setUrl("PictureUrl1");
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


    @After
    public void cleanUp() {
        picture = null;
    }
}
