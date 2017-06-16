package at.qe.sepm.asn_app.tests.constraints;

import at.qe.sepm.asn_app.models.Gender;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.child.Sibling;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.models.nursery.Task;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.ownExceptions.BirthdayConstraintException;
import at.qe.sepm.asn_app.ownExceptions.ParentConstraintException;
import at.qe.sepm.asn_app.repositories.ParentRepository;
import at.qe.sepm.asn_app.services.ParentService;
import at.qe.sepm.asn_app.ui.constraints.ParentConstraints;
import org.junit.Ignore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 08.05.17.
 */
@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ParentConstraintsTest {

    @Autowired
    ParentService parentService;
    @Autowired
    ParentConstraints parentConstraints;
    private Child child;
    private Parent parent;


    /**
     * Initialize every attribute with default values.
     */
    @Before
    public void initialize() {
        Set<Child> children = new HashSet<>();
        Set<Task> tasks = new HashSet<>();

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
        parent.setReligion(Religion.ISLAM);
        parent.setPhoneNumber("4839014056");
        parent.setNotification(true);
        parent.setChildren(children);
        parent.setTasks(tasks);
        parent.setFamilyStatus(FamilyStatus.VERHEIRATET);
        parent.setStatus(true);
    }


    /**
     * No constraint violation
     */
    @Test
    public void checkBirthdayConstraintsTest1() throws BirthdayConstraintException {
        parent.setBirthday("24/05/1980");
        ParentConstraints.checkBirthdayConstraints(parent);
    }


    /**
     * Check for the violation of the constraint that a parent must not be younger than 14 years.
     *
     * @throws BirthdayConstraintException if the birthday constraints have been violated.
     */
    @Test
    public void checkBirthdayConstraintsTest2() throws BirthdayConstraintException {
        parent.setBirthday("07/07/2003");
        assertFalse(ParentConstraints.checkBirthdayConstraints(parent));
    }


    /**
     * Check for the violation of the constraint that a parent must not be older than 99 years.
     *
     * @throws BirthdayConstraintException if the birthday constraints have been violated.
     */
    @Test
    public void checkBirthdayConstraintsTest3() throws BirthdayConstraintException {
        parent.setBirthday("15/06/1917");
        assertFalse(ParentConstraints.checkBirthdayConstraints(parent));
    }


    @Test
    public void alreadyExistsTest() {
        // The test should fail if the parent is not in the database yet.
        assertFalse(parentConstraints.alreadyExists(parent));

        // Save the parent in the database and check if the test now succeeds.
        parentService.saveParent(parent);
        assertTrue(parentConstraints.alreadyExists(parent));

        // Delete the parent again and check if it doesn't exist anymore.
        parentService.deleteParent(parent);
        assertFalse(parentConstraints.alreadyExists(parent));
    }


    /**
     * Set every attribute to null so it is assured that every test works with clean attributes.
     */
    @After
    public void cleanUp() {
        child = null;
        parent = null;
    }
}
