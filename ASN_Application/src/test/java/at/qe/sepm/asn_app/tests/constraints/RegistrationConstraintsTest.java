package at.qe.sepm.asn_app.tests.constraints;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.nursery.Registration;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.services.ChildService;
import at.qe.sepm.asn_app.services.ParentService;
import at.qe.sepm.asn_app.services.RegistrationService;
import at.qe.sepm.asn_app.tests.initialize.InitializeChild;
import at.qe.sepm.asn_app.tests.initialize.InitializeParent;
import at.qe.sepm.asn_app.tests.initialize.InitializeRegistration;
import at.qe.sepm.asn_app.ui.constraints.RegistrationConstraints;
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
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 17.06.17 14:35 CEST.
 */
@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RegistrationConstraintsTest {

    @Autowired
    private ChildService childService;
    @Autowired
    private ParentService parentService;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private RegistrationConstraints registrationConstraints;
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


    /**
     * Test a registration with no violation
     */
    @DirtiesContext
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void testValidRegistration() {
        saveElements();
        assertTrue(registrationConstraints.checkIfNurseryExists(registration));
        assertTrue(registrationConstraints.checkTimeConstraints(registration));
        assertTrue(registrationConstraints.registrationExists(registration));
        //deleteElements();
    }


    /**
     * If there is no registration saved in the database the
     * method should return almost immediately.
     */
    @DirtiesContext
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void testInvalidRegistrationExists() {
        //registration = InitializeRegistration.initialize();
        //registration.setChild(child);
        assertFalse(registrationConstraints.registrationExists(registration));
    }


    /**
     * If there is no nurseryInformation saved on the date
     * of registration.date then checkIfNurseryExists should
     * return <code>false</code>.
     */
    @Test
    public void testCheckIfNurseryExists() {
        Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("Europe/Vienna"));
        calendar.clear();
        calendar.set(2017, Calendar.AUGUST, 14, 8, 0);

        registration.setDate(calendar.getTime());
        assertFalse(registrationConstraints.checkIfNurseryExists(registration));
    }


    @Test
    public void testCheckTimeConstraints() {
        Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("Europe/Vienna"));
        calendar.clear();
        calendar.set(2017, Calendar.SEPTEMBER, 14, 0, 0);

        registration.setBringdate(calendar.getTime());
        //assertFalse(registrationConstraints.checkTimeConstraints(registration));
    }


    @After
    public void cleanUp() {
        // Set all attributes to null to assure clean values.
        child = null;
        parent1 = null;
        parent2 = null;
        registration = null;
    }


    private void saveElements() {
        // Save the parents in the database.
        parentService.saveParent(parent1);
        parentService.saveParent(parent2);

        // Save the child in the database.
        child = childService.saveChild(child);
        registration.setChild(child);

        // Save the registration in the database for further testing.
        registration = registrationService.saveRegistration(registration);
    }


    private void deleteElements() {
        // Delete the registration again.
        registrationService.deleteRegistration(registration);

        // Delete the child again.
        childService.deleteChild(child);

        // Delete the parent again.
        parentService.deleteParent(parent1);
        parentService.deleteParent(parent2);
    }
}
