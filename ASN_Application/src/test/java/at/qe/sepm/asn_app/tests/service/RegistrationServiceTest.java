package at.qe.sepm.asn_app.tests.service;

import at.qe.sepm.asn_app.models.Gender;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.child.Custody;
import at.qe.sepm.asn_app.models.child.Sibling;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.models.nursery.Registration;
import at.qe.sepm.asn_app.models.nursery.Task;
import at.qe.sepm.asn_app.models.referencePerson.Caregiver;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.services.ChildService;
import at.qe.sepm.asn_app.services.ParentService;
import at.qe.sepm.asn_app.services.RegistrationService;
import at.qe.sepm.asn_app.tests.controller.ContextMocker;
import at.qe.sepm.asn_app.ui.controllers.*;
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
import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
        Set<Task> parentTasks1 = new HashSet<>();
        Set<Task> parentTasks2 = new HashSet<>();

        parent1 = new Parent();
        parent1.setBirthday("22/05/1990");
        parent1.setEmail("ParentEmail1@google.com");
        parent1.setFamilyStatus(FamilyStatus.VERHEIRATET);
        parent1.setFirstName("ParentFirstName1");
        parent1.setImgName("ParentImgName1");
        parent1.setLastName("ParentLastName1");
        parent1.setLocation("ParentLocation1");
        parent1.setNotification(true);
        parent1.setPassword("passwd");
        parent1.setPhoneNumber("0123456789");
        parent1.setPostcode("6020");
        parent1.setReligion(Religion.CHRISTENTUM);
        parent1.setStatus(true);
        parent1.setStreetName("ParentStreetName1");
        parent1.setTasks(parentTasks1);
        parent1.setUsername("ParentUsername1");
        parent1.setUserRole(UserRole.PARENT);

        parent2 = new Parent();
        parent2.setBirthday("07/11/1978");
        parent2.setEmail("ParentEmail2@google.com");
        parent2.setFamilyStatus(FamilyStatus.VERHEIRATET);
        parent2.setFirstName("ParentFirstName2");
        parent2.setImgName("ParentImgName2");
        parent2.setLastName("ParentLastName2");
        parent2.setLocation("ParentLocation2");
        parent2.setNotification(true);
        parent2.setPassword("passwd");
        parent2.setPhoneNumber("0123456789");
        parent2.setPostcode("6020");
        parent2.setReligion(Religion.CHRISTENTUM);
        parent2.setStatus(true);
        parent2.setStreetName("ParentStreetName2");
        parent2.setTasks(parentTasks2);
        parent2.setUsername("ParentUsername2");
        parent2.setUserRole(UserRole.PARENT);

        Set<String> allergies = new HashSet<>();
        Set<String> foodIntolerances = new HashSet<>();
        Set<Sibling> siblings = new HashSet<>();
        Set<Caregiver> caregivers = new HashSet<>();

        child = new Child();
        child.setFirstName("ChildFirstName");
        child.setLastName("ChildLastName");
        child.setBirthday("22/05/2015");
        child.setImgName("ChildImgName");
        child.setGender(Gender.MAENNLICH);
        child.setPrimaryParent(parent1);
        child.setParent2(parent2);
        child.setEmergencyNumber("01234456789");
        //child.setAllergies(allergies);
        //child.setFoodIntolerances(foodIntolerances);
        child.setSiblings(siblings);
        child.setCustody(Custody.BEIDE);
        child.setReligion(Religion.CHRISTENTUM);
        child.setCaregivers(caregivers);

        Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("Europe/Vienna"));
        calendar.clear();
        calendar.set(2017, 5, 14, 8, 0);
        Date bringDate = calendar.getTime();

        calendar.clear();
        calendar.set(2017, 5, 14, 0, 0);
        Date date = calendar.getTime();

        registration = new Registration();
        registration.setBringdate(bringDate);
        registration.setChild(child);
        registration.setDate(date);
        registration.setNote("RegistrationNote1");
    }


    @DirtiesContext
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void test1() {
        // Save the parents in the database.
        parentService.saveParent(parent1);
        parentService.saveParent(parent2);

        // Save the child in the database.
        childService.saveChild(child);

        // Save a Registration in the database.
        registration = registrationService.saveRegistration(registration);

        // Check if the values have changed since the registration was saved.
        Registration other = registrationService.loadRegistration(registration.getId());
        assertTrue(registration.equals(other));

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


    @After
    public void cleanUp() {
        registration = null;
    }
}