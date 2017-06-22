package at.qe.sepm.asn_app.tests.service;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.nursery.Registration;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.services.ChildService;
import at.qe.sepm.asn_app.services.ParentService;
import at.qe.sepm.asn_app.services.RegistrationService;
import at.qe.sepm.asn_app.tests.initialize.InitializeChild;
import at.qe.sepm.asn_app.tests.initialize.InitializeParent;
import at.qe.sepm.asn_app.tests.initialize.InitializeRegistration;
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

import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 14.06.17 12:16 CEST.
 */
@Component
@Scope("view")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RegistrationServiceTest {

    @Autowired
    private ChildService childService;
    @Autowired
    private ParentService parentService;
    @Autowired
    private RegistrationService registrationService;
    private Child child;
    private Parent parent1;
    private Parent parent2;
    private Registration registration;


    @Before
    public void initialize() {
        child = InitializeChild.initialize();
        parent1 = InitializeParent.initialize1();
        parent2 = InitializeParent.initialize2();
        child.setPrimaryParent(parent1);
        child.setParent2(parent2);
        registration = InitializeRegistration.initialize();
    }


    @DirtiesContext
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void test1() {
        // Save the parents in the database.
        parentService.saveParent(parent1);
        parentService.saveParent(parent2);

        // Save the child in the database.
        child = childService.saveChild(child);

        // Save a Registration in the database.
        registration.setChild(child);
        registration = registrationService.saveRegistration(registration);

        // Check if the values have changed since the registration was saved.
        Registration other = registrationService.loadRegistration(registration.getId());
        assertTrue(registration.equals(other));

        Collection<Registration> registrations = registrationService.getAllRegistrationsByDate(new Date());
        assertNotNull(registrations);

        Collection<Registration> registrations1 = registrationService.getAllRegistrationsByChild(child.getId());
        assertNotNull(registrations1);


        // Delete the registration again.
        registrationService.deleteRegistration(registration);
        other = registrationService.loadRegistration(registration.getId());
        assertFalse(registration.equals(other));
        assertNull(other);

        // Delete the child again.
        childService.deleteChild(child);

        // Delete the parent again.
        parentService.deleteParent(parent1);
        parentService.deleteParent(parent2);
    }

    @Test
    public void testFurtherMethods() {
        // Test toString()
        assertNotNull(registration.toString());
        assertNotEquals("", registration.toString());
    }


    @After
    public void cleanUp() {
        child = null;
        parent1 = null;
        parent2 = null;
        registration = null;
    }
}