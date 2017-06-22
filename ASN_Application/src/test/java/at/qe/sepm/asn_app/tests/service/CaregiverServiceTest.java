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
import at.qe.sepm.asn_app.tests.initialize.InitializeChild;
import at.qe.sepm.asn_app.tests.initialize.InitializeParent;
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

import javax.xml.bind.SchemaOutputResolver;
import java.util.Collection;
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
    private Caregiver caregiver1;
    private Parent parent1;
    private Parent parent2;
    private Child child;


    @Before
    public void initialize() {


        parent1 = InitializeParent.initialize1();
        parent2 = InitializeParent.initialize2();
        child = InitializeChild.initialize();
        child.setPrimaryParent(parent1);
        child.setParent2(parent2);


        caregiver = new Caregiver();
        caregiver.setEligible(true);
        caregiver.setFirstName("CaregiverFirstName1");
        caregiver.setLastName("CaregiverLastName1");
        caregiver.setRelationship(Relationship.AUNT_UNCLE);
        caregiver.setImgName("CaregiverImgName1");
        caregiver.setPhoneNumber("0123456789");
        caregiver.setChild(child);

        caregiver1 = new Caregiver();
        caregiver1.setFirstName("CaregiverFirstName2");
        caregiver1.setLastName("CaregiverLastName2");
        caregiver1.setRelationship(Relationship.AUNT_UNCLE);
        caregiver1.setImgName("CaregiverImgName2");
        caregiver1.setPhoneNumber("0123456789");
        caregiver1.setChild(child);
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
        caregiver1.setChild(child);

        caregiver = caregiverService.saveCaregiver(caregiver);
        caregiver1 = caregiverService.saveCaregiver(caregiver1);

        Collection<Caregiver> caregivers = caregiverService.getAllCaregiversByEligibleTrue();
        assertNotNull(caregivers.contains(caregiver));

        Collection<Caregiver> careGivers = caregiverService.getAllCaregiversByEligibleFalse();
        assertNotNull(careGivers.contains(caregiver1));

        Collection<Caregiver> CareGivers = caregiverService.getCaregiversForParent(parent1.getUsername());
        assertNotNull(CareGivers.contains(caregiver1));

        // Check if the values have changed since the caregiver was saved.
        Caregiver other = caregiverService.loadCaregiver(caregiver.getId());
        assertTrue(caregiver.equals(other));

        // Delete the reference of the caregiver from the child.
        child.getCaregivers().remove(caregiver);
        child.getCaregivers().remove(caregiver1);
        child = childService.saveChild(child);



        // Delete the caregiver again
        caregiverService.deleteCaregiver(caregiver);
        caregiverService.deleteCaregiver(caregiver1);
        System.out.println(caregiverService.loadCaregiver(caregiver.getId()));



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
