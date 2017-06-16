package at.qe.sepm.asn_app.tests.service;

import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.models.nursery.Task;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.services.ParentService;
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

import javax.faces.context.FacesContext;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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


    @After
    public void cleanUp() {
        parent = null;
    }
}