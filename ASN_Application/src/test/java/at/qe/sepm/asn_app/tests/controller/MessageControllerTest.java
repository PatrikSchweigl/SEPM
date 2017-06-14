package at.qe.sepm.asn_app.tests.controller;

import at.qe.sepm.asn_app.models.nursery.Message;
import at.qe.sepm.asn_app.services.MessageService;
import at.qe.sepm.asn_app.ui.controllers.MessageController;
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
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 10.06.17 16:03 CEST.
 */
@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MessageControllerTest {

    @Autowired
    private MessageController messageController;
    @Autowired
    private MessageService messageService;
    private Message message;


    @Before
    public void initialize() {
        Date date = new Date();
        date.setYear(2017);
        date.setMonth(6);
        date.setDate(19);

        message = new Message();
        message.setDate(date);
        message.setMessage("Message1");
        message.setUsername("Username1");
    }


    @DirtiesContext
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void test1() {
        // Save the message in the database.
        messageController.setMessage2(message);
        message = messageController.doSaveMessage();

        // Check if the values have changed since the message was saved.
        Message other = messageService.loadMessage(message.getId());
        assertTrue(message.equals(other));

        // Delete the message again
        messageController.doDeleteMessage(message);
        other = messageService.loadMessage(message.getId());
        assertFalse(message.equals(other));
        assertNull(other);
    }


    @After
    public void cleanUp() {
        message = null;
    }
}
