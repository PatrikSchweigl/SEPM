package at.qe.sepm.asn_app.tests.database;

import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
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
public class ParentDBTest {

    @Autowired
    ParentController parentController;
    @Autowired
    ParentEditController parentEditController;
    @Autowired
    private ParentService parentService;

    private Parent parent;


    @Before
    public void initialize() {
        Set<Child> parentListChildren1 = new HashSet<>();
        Set<Task> parentListTasks1 = new HashSet<>();
        parent = new Parent("", "passwd", "ParentFirstName1", "ParentLastName1", "ParentLocation1",
                "ParentStreetName1", "ParentPostcode1", "24/05/1980","ParentEmail1@google.com",
                UserRole.PARENT, "ParentImageName1", parentListChildren1, parentListTasks1,
                FamilyStatus.VERHEIRATET, true);
    }


    @Test
    public void test1() {
        // Save a parent in the database
        parentController.setParent2(parent);
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
