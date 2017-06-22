package at.qe.sepm.asn_app.tests.controller;

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
import at.qe.sepm.asn_app.ui.controllers.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.faces.context.FacesContext;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 04.06.17 19:25 CEST.
 */
@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CaregiverControllerTest {

    @Autowired
    private CaregiverController caregiverController;
    @Autowired
    private CaregiverService caregiverService;
    @Autowired
    private ParentController parentController;
    @Autowired
    private ParentEditController parentEditController;
    @Autowired
    private ChildController childController;
    @Autowired
    private ChildEditController childEditController;
    @Autowired
    private ChildService childService;
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
        caregiver.setFirstName("CaregiverFirstName1");
        caregiver.setLastName("CaregiverLastName1");
        caregiver.setRelationship(Relationship.AUNT_UNCLE);
        caregiver.setImgName("CaregiverImgName1");
        caregiver.setPhoneNumber("0123456789");
        //caregiver.setChild(child);
    }


    @Test
    public void test1() {
        FacesContext context = ContextMocker.mockFacesContext();
        RequestContext requestContext = ContextMocker.mockRequestContext();

        try {
            // Save the parents in the database.
            parentController.setParent(parent1);
            parentController.doSaveParent();
            parentController.setParent(parent2);
            parentController.doSaveParent();

            // Save the child in the database.
            childController.setChild2(child);
            childController.setParentUserName(parent1.getUsername());
            child = childController.doSaveChild();

            caregiver.setChild(child);

            // Save a caregiver in the database
            caregiverController.setCaregiver2(caregiver);
            //childService.setId(child.getId());
            caregiver = caregiverController.doSaveCaregiver();

            // Check if the values have changed since the caregiver was saved.
            Caregiver other = caregiverService.loadCaregiver(caregiver.getId());
            assertTrue(caregiver.equals(other));

            // Delete the caregiver again
            //caregiverController.setCaregiverEdit(caregiver);
            //caregiverController.doDeleteCaregiverEdit();
            //other = caregiverService.loadCaregiver(caregiver.getId());
            //assertFalse(caregiver.equals(other));
            //assertNull(other);

            // Delete the child again.
            childEditController.setChildEdit(child);
            childEditController.doDeleteChild();

            // Delete the parent again.
            parentEditController.setParent2(parent1);
            //parentEditController.doDeleteParent();
            parentEditController.setParent2(parent2);
            //parentEditController.doDeleteParent();
        }
        finally {
            context.release();
            requestContext.release();
        }
    }


    @After
    public void cleanUp() {
        caregiver = null;
        parent1 = null;
        parent2 = null;
        child = null;
    }
}
