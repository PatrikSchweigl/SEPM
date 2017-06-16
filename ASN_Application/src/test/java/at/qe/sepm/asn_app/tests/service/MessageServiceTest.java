package at.qe.sepm.asn_app.tests.service;

import at.qe.sepm.asn_app.models.nursery.Message;
import at.qe.sepm.asn_app.services.MessageService;
import at.qe.sepm.asn_app.tests.controller.ContextMocker;
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
import org.springframework.test.context.web.WebAppConfiguration;

import javax.faces.context.FacesContext;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 14.06.17 12:05 CEST.
 */
@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;
    private Message message;


    @Before
    public void initialize() {
        Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("Europe/Vienna"));
        calendar.clear();
        calendar.set(2017, Calendar.APRIL, 23, 13, 45);
        Date date = calendar.getTime();

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
        message = messageService.saveMessage(message);

        // Check if the values have changed since the message was saved.
        Message other = messageService.loadMessage(message.getId());
        assertTrue(message.equals(other));

        // Delete the message again
        messageService.deleteMessage(message);
        other = messageService.loadMessage(message.getId());
        assertFalse(message.equals(other));
        assertNull(other);
    }


    @After
    public void cleanUp() {
        message = null;
    }
}
