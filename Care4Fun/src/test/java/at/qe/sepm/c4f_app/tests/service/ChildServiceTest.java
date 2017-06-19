package at.qe.sepm.c4f_app.tests.service;

import at.qe.sepm.c4f_app.models.Gender;
import at.qe.sepm.c4f_app.models.UserRole;
import at.qe.sepm.c4f_app.models.child.Child;
import at.qe.sepm.c4f_app.models.child.Custody;
import at.qe.sepm.c4f_app.models.child.Sibling;
import at.qe.sepm.c4f_app.models.general.FamilyStatus;
import at.qe.sepm.c4f_app.models.general.Religion;
import at.qe.sepm.c4f_app.models.nursery.Task;
import at.qe.sepm.c4f_app.models.referencePerson.Caregiver;
import at.qe.sepm.c4f_app.models.referencePerson.Parent;
import at.qe.sepm.c4f_app.services.ChildService;
import at.qe.sepm.c4f_app.services.ParentService;
import at.qe.sepm.c4f_app.tests.initialize.InitializeSibling;
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
 * on 14.06.17 11:39 CEST.
 */
@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ChildServiceTest {

    @Autowired
    private ChildService childService;
    @Autowired
    private ParentService parentService;
    private Child child;
    private Parent parent1;
    private Parent parent2;
    private Sibling sibling1;


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

        // Check if the values have changed since the child was saved.
        Child other = childService.loadChild(child.getId());
        assertTrue(child.equals(other));

        // Delete the child again.
        childService.deleteChild(child);
        other = childService.loadChild(child.getId());
        assertFalse(child.equals(other));
        assertNull(other);

        // Delete the parent again.
        parentService.deleteParent(parent1);
        parentService.deleteParent(parent2);
    }


    @Test
    public void testSetterGetter() {
        // Initialize attributes
        Set<String> allergies = new HashSet<>();
        String birthday = "22/05/2015";
        Set<Caregiver> caregivers = new HashSet<>();
        Custody custody = Custody.VATER;
        String emergencyNumber = "53ß45435239802";
        String firstName = "ChildFirstName";
        Set<String> foodIntolerances = new HashSet<>();
        Gender gender = Gender.ANDERES;
        String imgName = "ChildImgName";
        String lastName = "ChildLastName";
        Religion religion = Religion.ISLAM;
        Sibling sibling = InitializeSibling.initialize1();
        Set<Sibling> siblings = new HashSet<>();

        // Set attributes
        child = new Child();
        //child.setAllergies(allergies);
        child.setBirthday(birthday);
        child.setCaregivers(caregivers);
        child.setCustody(custody);
        child.setEmergencyNumber(emergencyNumber);
        child.setFirstName(firstName);
        //child.setFoodIntolerances(foodIntolerances);
        child.setGender(gender);
        child.setImgName(imgName);
        child.setLastName(lastName);
        child.setPrimaryParent(parent1);
        child.setParent2(parent2);
        child.setReligion(religion);
        child.setSiblings(siblings);
        child.addSibling(sibling);

        // Compare attributes with getter
        assertEquals(birthday, child.getBirthday());
        assertEquals(caregivers, child.getCaregivers());
        assertEquals(custody, child.getCustody());
        assertEquals(emergencyNumber, child.getEmergencyNumber());
        assertEquals(firstName + " " + lastName, child.getFullname());
        assertEquals(firstName, child.getFirstName());
        assertEquals(gender, child.getGender());
        assertEquals(imgName, child.getImgName());
        assertEquals(lastName, child.getLastName());
        assertEquals(parent1, child.getPrimaryParent());
        assertEquals(parent1.getFirstName() + " " + parent1.getLastName(), child.getPrimaryParentFullName());
        assertEquals(parent2, child.getParent2());
        assertEquals(religion, child.getReligion());
        assertEquals(siblings, child.getSiblings());
    }


    @Test
    public void testFurtherMethods() {
        // Test toString()
        assertNotEquals("", child.toString());

        // Test isNew()
        assertFalse(child.isNew());

        child = new Child();
        child.setFirstName("ChildIsNewFirstName");
        assertFalse(child.isNew());

        child = new Child();
        child.setLastName("ChildIsNewLastName");
        assertFalse(child.isNew());

        child = new Child();
        assertTrue(child.isNew());
    }


    @After
    public void cleanUp() {
        child = null;
        parent1 = null;
        parent2 = null;
        sibling1 = null;
    }
}
