package at.qe.sepm.asn_app.tests.service;

import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.models.nursery.Task;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.services.ParentService;
import at.qe.sepm.asn_app.tests.initialize.InitializeParent;
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

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 14.06.17 12:10 CEST.
 */
@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ParentServiceTest {

    @Autowired
    private ParentService parentService;
    private Parent parent;


    @Before
    public void initialize() {
        parent = InitializeParent.initialize1();
    }


    @DirtiesContext
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void test1() {
        // Save a parent in the database
        parentService.saveParent(parent);

        // Check if the values have changed since the parent was saved.
        Parent other = parentService.loadParent(parent.getUsername());
        assertTrue(parent.equals(other));

        // Delete the parent again
        parentService.deleteParent(parent);
        other = parentService.loadParent(parent.getUsername());
        assertFalse(parent.equals(other));
        assertNull(other);
    }


    @Test
    public void testSetterGetter() {
        // Initialize attributes
        String birthday = "24/05/1980";
        Set<Child> children = new HashSet<>();
        String eMail = "ParentEmail@google.com";
        FamilyStatus familyStatus = FamilyStatus.GESCHIEDEN;
        String firstName = "ParentFirstName";
        String imgName = "ParentImgName";
        String lastName = "ParentLastName";
        String location = "parentLocation";
        boolean notification = true;
        String password = "passwd";
        String phoneNumber = "28976ß782834";
        String postcode = "6020";
        Religion religion = Religion.HINDUISMUS;
        boolean status = true;
        Set<Task> tasks = new HashSet<>();
        String streetName = "StreetName";
        String username = "ParentUsername";
        UserRole userRole = UserRole.PARENT;

        // Set attributes
        parent = new Parent();
        parent.setBirthday(birthday);
        parent.setChildren(children);
        parent.setEmail(eMail);
        parent.setFamilyStatus(familyStatus);
        parent.setFirstName(firstName);
        parent.setImgName(imgName);
        parent.setLastName(lastName);
        parent.setLocation(location);
        parent.setNotification(notification);
        parent.setPassword(password);
        parent.setPhoneNumber(phoneNumber);
        parent.setPostcode(postcode);
        parent.setReligion(religion);
        parent.setStatus(status);
        parent.setStreetName(streetName);
        parent.setTasks(tasks);
        parent.setUsername(username);
        parent.setUserRole(userRole);

        // Compare all attributes with getters.
        assertEquals(birthday, parent.getBirthday());
        assertEquals(children, parent.getChildren());
        assertEquals(eMail, parent.getEmail());
        assertEquals(familyStatus, parent.getFamilyStatus());
        assertEquals(firstName, parent.getFirstName());
        assertEquals(imgName, parent.getImgName());
        assertEquals(lastName, parent.getLastName());
        assertEquals(location, parent.getLocation());
        assertEquals(notification, parent.isNotification());
        assertEquals(password, parent.getPassword());
        assertEquals(phoneNumber, parent.getPhoneNumber());
        assertEquals(postcode, parent.getPostcode());
        assertEquals(religion, parent.getReligion());
        assertEquals(streetName, parent.getStreetName());
        assertEquals(tasks, parent.getTasks());
        assertEquals(username, parent.getUsername());
        assertEquals(userRole, parent.getUserRole());
    }


    @After
    public void cleanUp() {
        parent = null;
    }
}