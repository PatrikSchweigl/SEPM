package at.qe.sepm.asn_app.tests.service;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.models.nursery.Task;
import at.qe.sepm.asn_app.services.TaskService;
import at.qe.sepm.asn_app.services.UserService;
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
 * on 14.06.17 12:28 CEST.
 */
@Component
@Scope("view")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    private Task task;

    private Date beginDate;
    private String description;
    private Date endingDate;
    private boolean important;
    private UserData receiver;
    private UserData sender;
    private String stringId;
    private String styleClass;
    private boolean status;


    @Before
    public void initialize() {
        // Initialize receiver
        receiver = new UserData();
        receiver.setBirthday("22/05/1989");
        receiver.setEmail("TaskUserDataEmail1@google.com");
        receiver.setFirstName("TaskUserDataFirstName1");
        receiver.setLastName("TaskUserDataLastName1");
        receiver.setImgName("TaskUserDataImgName1");
        receiver.setLocation("TaskUserDataLocation1");
        receiver.setNotification(true);
        receiver.setPassword("passwd");
        receiver.setPhoneNumber("0123456789");
        receiver.setPostcode("6020");
        receiver.setReligion(Religion.CHRISTENTUM);
        receiver.setStreetName("TaskUserDataStreetName1");
        receiver.setUsername("TaskUserDataUsername1");
        receiver.setUserRole(UserRole.PARENT);

        // Initialize sender
        sender = new UserData();
        sender.setBirthday("07/03/1985");
        sender.setEmail("TaskUserDataEmail2@google.com");
        sender.setFirstName("TaskUserDataFirstName2");
        sender.setLastName("TaskUserDataLastName2");
        sender.setImgName("TaskUserDataImgName2");
        sender.setLocation("TaskUserDataLocation2");
        sender.setNotification(true);
        sender.setPassword("passwd");
        sender.setPhoneNumber("9876543210");
        sender.setPostcode("6020");
        sender.setReligion(Religion.CHRISTENTUM);
        sender.setStreetName("TaskUserDataStreetName2");
        sender.setUsername("TaskUserDataUsername2");
        sender.setUserRole(UserRole.PARENT);

        // Initialize task
        Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("Europe/Vienna"));
        calendar.clear();
        calendar.set(2017, Calendar.JUNE, 26, 8, 0);
        beginDate = calendar.getTime();

        calendar.clear();
        calendar.set(2017, Calendar.JUNE, 26, 12, 0);
        endingDate = calendar.getTime();

        description = "TaskDescription";
        important = true;
        stringId = "TaskStringId";
        styleClass = "TaskStyleClass";
        status = true;

        task = new Task();
        task.setBeginDate(beginDate);
        task.setDescription(description);
        task.setEndingDate(endingDate);
        task.setImportant(important);
        task.setReceiver(receiver);
        task.setSender(sender);
        task.setStringId(stringId);
        task.setStyleClass(styleClass);
        task.setTaskStatus(status);
    }


    @DirtiesContext
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void test1() {
        // Save the sender and receiver in the database;
        userService.saveUser(sender);
        userService.saveUser(receiver);

        // Save a Task in the database.
        task = taskService.saveTask(task);

        // Check if the values have changed since the task was saved.
        Task other = taskService.loadTask(task.getId());
        assertTrue(task.equals(other));

        // Delete the task again.
        taskService.deleteTask(task);
        other = taskService.loadTask(task.getId());
        assertFalse(task.equals(other));
        assertNull(other);

        // Delete the sender and receiver again.
        userService.deleteUser(sender);
        userService.deleteUser(receiver);
    }


    /**
     * This test is just for completeness to make sure
     * that everything works and is covered 100%.
     */
    @Test
    public void testSetterGetter() {
        // Compare attributes with getter
        assertEquals(beginDate, task.getBeginDate());
        assertEquals(description, task.getDescription());
        assertEquals(endingDate, task.getEndingDate());
        assertEquals(important, task.getImportant());
        assertEquals(receiver, task.getReceiver());
        assertEquals(sender, task.getSender());
        assertEquals(stringId, task.getStringId());
        assertEquals(styleClass, task.getStyleClass());
        assertEquals(status, task.isTaskStatus());
    }


    @Test
    public void testFurtherMethods() {
        // Test toString()
        assertNotNull(task.toString());
        assertNotEquals("", task.toString());
        System.out.println(task.toString());

        // Test isNew()
        task = new Task();
        assertTrue(task.isNew());
        task.setSender(sender);
        assertFalse(task.isNew());

        // Test getFormattedDate
        assertNotEquals("", task.getFormattedDate(beginDate));
        System.out.println(task.getFormattedDate(beginDate));
    }


    @After
    public void cleanUp() {
        task = null;
    }
}
