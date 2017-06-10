package at.qe.sepm.asn_app.tests.database;

import at.qe.sepm.asn_app.models.Gender;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.child.Custody;
import at.qe.sepm.asn_app.models.child.Sibling;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.models.referencePerson.Caregiver;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.models.referencePerson.Relationship;
import at.qe.sepm.asn_app.services.CaregiverService;
import at.qe.sepm.asn_app.services.ChildService;
import at.qe.sepm.asn_app.ui.controllers.CaregiverController;
import at.qe.sepm.asn_app.ui.controllers.ChildController;
import at.qe.sepm.asn_app.ui.controllers.ParentController;
import at.qe.sepm.asn_app.ui.controllers.ParentEditController;
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
    private ChildService childService;
    private Caregiver caregiver;
    private Parent parent1;
    private Parent parent2;
    private Child child;


    @Before
    public void initialize() {
        caregiver = new Caregiver();
        caregiver.setFirstName("CaregiverFirstName1");
        caregiver.setLastName("CaregiverLastName1");
        caregiver.setRelationship(Relationship.AUNT_UNCLE);
        caregiver.setImgName("CaregiverImgName1");
        caregiver.setPhoneNumber("0123456789");

        parent1 = new Parent();
        parent1.setFirstName("ParentFirstName1");
        parent1.setLastName("ParentLastName1");
        parent1.setUsername("ParentUsername1");
        parent1.setPassword("passwd");
        parent1.setUserRole(UserRole.PARENT);
        parent1.setFamilyStatus(FamilyStatus.VERHEIRATET);
        parent1.setBirthday("22/05/1990");

        parent2 = new Parent();
        parent2.setFirstName("ParentFirstName2");
        parent2.setLastName("ParentLastName2");
        parent2.setUsername("ParentUsername2");
        parent2.setPassword("passwd");
        parent2.setUserRole(UserRole.PARENT);
        parent2.setFamilyStatus(FamilyStatus.VERHEIRATET);
        parent2.setBirthday("07/11/1978");

        Set<String> allergies = new HashSet<>();
        Set<String> foodIntolerances = new HashSet<>();
        Set<Sibling> siblings = new HashSet<>();
        Set<Caregiver> caregivers = new HashSet<>();

        child = new Child();
        child.setFirstName("ChildFirstName");
        child.setLastName("ChildLastName");
        child.setBirthday("22/05/2015");
        child.setImgName("ChildImgName");
        child.setGender(Gender.MALE);
        child.setPrimaryParent(parent1);
        child.setParent2(parent2);
        child.setEmergencyNumber("01234456789");
        child.setAllergies(allergies);
        child.setFoodIntolerances(foodIntolerances);
        child.setSiblings(siblings);
        child.setCustody(Custody.BOTH);
        child.setReligion(Religion.CHRISTENTUM);
        child.setCaregivers(caregivers);
    }


    @Test
    public void test1() {
        // Save the parents in the database.
        parentController.setParent2(parent1);
        parentController.doSaveParent();
        parentController.setParent2(parent2);
        parentController.doSaveParent();

        // Save the child in the database.
        childController.setChild2(child);
        childController.setParentUserName(parent1.getUsername());
        child = childController.doSaveChild();

        // Save a caregiver in the database
        caregiverController.setCaregiver2(caregiver);
        childService.setId(child.getId());
        caregiver = caregiverController.doSaveCaregiver();

        // Check if the values have changed since the caregiver was saved.
        Caregiver other = caregiverService.loadCaregiver(caregiver.getId());
        assertTrue(caregiver.equals(other));

        // Delete the parent again.
        parentEditController.setParent2(parent1);
        parentEditController.doDeleteParent();
        parentEditController.setParent2(parent2);
        parentEditController.doDeleteParent();

        // Delete the caregiver again
        caregiverController.setCaregiverEdit2(caregiver);
        caregiverController.doDeleteCaregiverEdit();
        other = caregiverService.loadCaregiver(caregiver.getId());
        assertFalse(caregiver.equals(other));
        assertNull(other);

        // Delete the child again.
        childController.setChildEdit2(child);
        childController.doDeleteChild();
    }


    @After
    public void cleanUp() {
        caregiver = null;
        parent1 = null;
        parent2 = null;
        child = null;
    }
}
