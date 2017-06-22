package at.qe.sepm.asn_app.tests.controller;

import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.models.nursery.Task;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.services.ParentService;
import at.qe.sepm.asn_app.ui.controllers.ParentController;
import at.qe.sepm.asn_app.ui.controllers.ParentEditController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 03.06.17 16:49 CEST.
 */
@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ParentControllerTest {

    @Autowired
    private ParentController parentController;
    @Autowired
    private ParentEditController parentEditController;
    @Autowired
    private ParentService parentService;
    private Parent parent;


    @Before
    public void initialize() {
        Set<Child> parentChildren1 = new HashSet<>();
        Set<Task> parentTasks1 = new HashSet<>();

        parent = new Parent();
        parent.setUsername("");
        parent.setPassword("passwd");
        parent.setFirstName("ParentFirstName1");
        parent.setLastName("ParentLastName1");
        parent.setLocation("ParentLocation1");
        parent.setStreetName("ParentStreetName1");
        parent.setPostcode("6020");
        parent.setBirthday("24/05/1980");
        parent.setEmail("ParentEmail1@google.com");
        parent.setImgName("ParentImgName1");
        parent.setUserRole(UserRole.PARENT);
        parent.setReligion(Religion.CHRISTENTUM);
        parent.setPhoneNumber("0123456789");
        parent.setNotification(true);
        parent.setChildren(parentChildren1);
        parent.setTasks(parentTasks1);
        parent.setFamilyStatus(FamilyStatus.VERHEIRATET);
        parent.setStatus(true);
    }


    @Test
    public void test1() {
        // Save a parent in the database
        parentController.setParent(parent);
        parentController.doSaveParent();

        // Check if the values have changed since the parent was saved.
        Parent other = parentService.loadParent(parent.getUsername());
        assertTrue(parent.equals(other));

        // Delete the parent again
        parentEditController.setParent2(parent);
        parentEditController.doDeleteParent();
        other = parentService.loadParent(parent.getUsername());
        assertFalse(parent.equals(other));
        assertNull(other);
    }


    @After
    public void cleanUp() {
        parent = null;
    }
}
