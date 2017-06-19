package at.qe.sepm.c4f_app.tests.service;

import at.qe.sepm.c4f_app.models.nursery.PrivateMessage;
import at.qe.sepm.c4f_app.services.PrivateMessageService;
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
import java.util.TimeZone;

import static org.junit.Assert.*;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 14.06.17 12:13 CEST.
 */
@Component
@Scope("view")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PrivateMessageServiceTest {

    @Autowired
    private PrivateMessageService privateMessageService;
    private PrivateMessage privateMessage;
    private Date date;
    private String message;
    private String usernameReceiver;
    private String usernameSender;


    @Before
    public void initialize() {
        Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("Europe/Vienna"));
        calendar.clear();
        calendar.set(2017, Calendar.APRIL, 23, 13, 45);

        // Initialize attributes
        date = calendar.getTime();
        message = "PrivateMessage";
        usernameReceiver = "UsernameReceiver";
        usernameSender = "UsernameSender";

        privateMessage = new PrivateMessage();
        privateMessage.setDate(date);
        privateMessage.setMessage(message);
        privateMessage.setUsernameReceiver(usernameReceiver);
        privateMessage.setUsernameSender(usernameSender);
    }


    @DirtiesContext
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void test1() {
        // Save a PrivateMessage in the database.
        privateMessage = privateMessageService.savePrivateMessage(privateMessage);

        // Check if the values have changed since the privateMessage was saved.
        PrivateMessage other = privateMessageService.loadPrivateMessage(privateMessage.getId());
        assertTrue(privateMessage.equals(other));

        // Delete the privateMessage again.
        privateMessageService.deletePrivateMessage(privateMessage);
        other = privateMessageService.loadPrivateMessage(privateMessage.getId());
        assertFalse(privateMessage.equals(other));
        assertNull(other);
    }


    @Test
    public void testSetterGetter() {
        // Compare attributes with getter
        assertEquals(date.toString(), privateMessage.getDate());
        assertEquals(message, privateMessage.getMessage());
        assertEquals(usernameReceiver, privateMessage.getUsernameReceiver());
        assertEquals(usernameSender, privateMessage.getUsernameSender());
    }


    @After
    public void cleanUp() {
        privateMessage = null;
    }
}