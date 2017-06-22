package at.qe.sepm.asn_app.tests.service;

import at.qe.sepm.asn_app.models.Gender;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.child.Custody;
import at.qe.sepm.asn_app.models.child.Sibling;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.models.nursery.Task;
import at.qe.sepm.asn_app.models.referencePerson.Caregiver;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.models.referencePerson.Relationship;
import at.qe.sepm.asn_app.services.CaregiverService;
import at.qe.sepm.asn_app.services.ChildService;
import at.qe.sepm.asn_app.services.ParentService;
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
 * on 14.06.17 11:24 CEST.
 */
@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CaregiverServiceTest {

    @Autowired
    private CaregiverService caregiverService;
    @Autowired
    private ChildService childService;
    @Autowired
    private ParentService parentService;
    private Caregiver caregiver;
    private Parent parent1;
    private Parent parent2;
    private Child child;


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

        caregiver = new Caregiver();
        caregiver.setEligible(true);
        caregiver.setFirstName("CaregiverFirstName1");
        caregiver.setLastName("CaregiverLastName1");
        caregiver.setRelationship(Relationship.AUNT_UNCLE);
        caregiver.setImgName("CaregiverImgName1");
        caregiver.setPhoneNumber("0123456789");
        caregiver.setChild(child);
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

        // Save a caregiver in the database
        caregiver.setChild(child);
        childService.setId(child.getId());
        caregiver = caregiverService.saveCaregiver(caregiver);

        // Check if the values have changed since the caregiver was saved.
        Caregiver other = caregiverService.loadCaregiver(caregiver.getId());
        assertTrue(caregiver.equals(other));

        // Delete the reference of the caregiver from the child.
        child.getCaregivers().remove(caregiver);
        //child = childService.saveChild(child);

        // Delete the caregiver again
        caregiverService.deleteCaregiver(caregiver);
        other = caregiverService.loadCaregiver(caregiver.getId());
        assertFalse(caregiver.equals(other));
        assertNull(other);

        // Delete the child again.
        childService.deleteChild(child);

        // Delete the parent again.
        parentService.deleteParent(parent1);
        parentService.deleteParent(parent2);
    }


    @Test
    public void testSetterGetter() {
        // Initialize attributes
        boolean eligible = true;
        String firstName = "CaregiverFirstName";
        String lastName = "CaregiverLastName";
        Relationship relationship = Relationship.GRANDPARENT;
        String imgName = "CaregiverImgName";
        String phoneNumber = "27851508914598";

        // Set attributes
        caregiver.setEligible(eligible);
        caregiver.setFirstName(firstName);
        caregiver.setLastName(lastName);
        caregiver.setRelationship(relationship);
        caregiver.setImgName(imgName);
        caregiver.setPhoneNumber(phoneNumber);

        // Compare attributes with getter
        assertEquals(eligible, caregiver.getEligible());
        assertEquals(child, caregiver.getChild());
        assertEquals(firstName, caregiver.getFirstName());
        assertEquals(lastName + " " + firstName, caregiver.getFullName());
        assertEquals(lastName, caregiver.getLastName());
        assertEquals(relationship, caregiver.getRelationship());
        assertEquals(imgName, caregiver.getImgName());
        assertEquals(phoneNumber, caregiver.getPhoneNumber());
    }


    @Test
    public void testFurtherMethods() {
        // Print all information with toString();
        assertNotNull(caregiver.toString());
        assertNotEquals("", caregiver.toString());


        // Test isNew()
        assertFalse(caregiver.isNew());

        caregiver = new Caregiver();
        caregiver.setFirstName("CaregiverIsNewFirstName");
        assertFalse(caregiver.isNew());

        caregiver = new Caregiver();
        caregiver.setLastName("CaregiverIsNewLastName");
        assertFalse(caregiver.isNew());

        caregiver = new Caregiver();
        assertTrue(caregiver.isNew());
    }


    @After
    public void cleanUp() {
        caregiver = null;
        parent1 = null;
        parent2 = null;
        child = null;
    }
}
