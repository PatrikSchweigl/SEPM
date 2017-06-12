package at.qe.sepm.asn_app.tests.controller;

import at.qe.sepm.asn_app.models.Gender;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.child.Custody;
import at.qe.sepm.asn_app.models.child.Sibling;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.models.referencePerson.Caregiver;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.services.ChildService;
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
 * on 04.06.17 12:09 CEST.
 */
@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ChildControllerTest {

    @Autowired
    private ChildController childController;
    @Autowired
    private ChildService childService;
    @Autowired
    private ParentController parentController;
    @Autowired
    private ParentEditController parentEditController;
    private Child child;
    private Parent parent1;
    private Parent parent2;
    private Sibling sibling1;


    @Before
    public void initialize() {
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
        child.setGender(Gender.MAENNLICH);
        child.setPrimaryParent(parent1);
        child.setParent2(parent2);
        child.setEmergencyNumber("01234456789");
        child.setAllergies(allergies);
        child.setFoodIntolerances(foodIntolerances);
        child.setSiblings(siblings);
        child.setCustody(Custody.BEIDE);
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
        childController.doSaveChild();


        // Check if the values have changed since the child was saved.
        Child other = childService.loadChild(child.getId());
        assertTrue(child.equals(other));

        // Delete the child again.
        childController.setChildEdit2(child);
        childController.doDeleteChild();
        other = childService.loadChild(child.getId());
        assertFalse(child.equals(other));
        assertNull(other);

        // Delete the parent again.
        parentEditController.setParent2(parent1);
        parentEditController.doDeleteParent();
        parentEditController.setParent2(parent2);
        parentEditController.doDeleteParent();
    }


    @After
    public void cleanUp() {
        child = null;
        parent1 = null;
        parent2 = null;
        sibling1 = null;
    }
}
