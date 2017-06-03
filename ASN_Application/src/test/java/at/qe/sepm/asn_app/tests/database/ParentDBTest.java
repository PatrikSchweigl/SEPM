package at.qe.sepm.asn_app.tests.database;

import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.nursery.Task;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.repositories.ParentRepository;
import at.qe.sepm.asn_app.services.ParentService;
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
    ParentRepository parentRepository;
    private Parent parent1;


    @Before
    public void initialize() {
        Set<Child> parentListChildren1 = new HashSet<>();
        Set<Task> parentListTasks1 = new HashSet<>();
        parent1 = new Parent("", "passwd", "ParentFirstName1", "ParentLastName1", "ParentLocation1",
                "ParentStreetName1", "ParentPostcode1", "24/05/1980","ParentEmail1@google.com",
                UserRole.PARENT, "ParentImageName1", parentListChildren1, parentListTasks1,
                FamilyStatus.VERHEIRATET, true);
    }


    @Test
    public void test1() {
        // Save a parent in the database
        parentRepository.save(parent1);

        // Check if the values have changed since the parent was saved.
        Parent other = parentRepository.findFirstByUsername(parent1.getUsername());
        assertTrue(parent1.equals(other));

        // Delete the parent again
        parentRepository.delete(parent1);
        other = parentRepository.findFirstByUsername(parent1.getUsername());
        assertFalse(parent1.equals(other));
        assertNull(other);
    }


    @After
    public void cleanUp() {
        parent1 = null;
    }
}
