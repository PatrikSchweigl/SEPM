package at.qe.sepm.asn_app.tests.service;

import at.qe.sepm.asn_app.models.Gender;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.child.Custody;
import at.qe.sepm.asn_app.models.child.Sibling;
import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.models.nursery.Lunch;
import at.qe.sepm.asn_app.models.referencePerson.Caregiver;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.services.ChildService;
import at.qe.sepm.asn_app.services.ParentService;
import at.qe.sepm.asn_app.tests.initialize.InitializeChild;
import at.qe.sepm.asn_app.tests.initialize.InitializeParent;
import at.qe.sepm.asn_app.tests.initialize.InitializeSibling;
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

import java.util.*;

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
    private Lunch lunch;


    @Before
    public void initialize() {
        parent1 = InitializeParent.initialize1();
        parent2 = InitializeParent.initialize2();
        child = InitializeChild.initialize();
        sibling1 = InitializeSibling.initialize1();


        //initialize lunch object
        Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("Europe/Vienna"));
        calendar.clear();
        calendar.set(2017, Calendar.APRIL, 23, 13, 45);
        Date date = calendar.getTime();
        Set<Long> childrenIds = new HashSet<>();

        lunch = new Lunch();
        lunch.setCost(5);
        lunch.setDate(date);
        lunch.setMeal("SpaghettiOs");
        lunch.setChildrenIds(childrenIds);

        Set<Child> children = new HashSet<>();
        children.add(child);
        parent1.setChildren(children);
        parent2.setChildren(children);
    }


    @DirtiesContext
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void test1() {
        // Save the parents in the database.
        parent1 = parentService.saveParent(parent1);
        parent2 = parentService.saveParent(parent2);

        // Save the child in the database.
        child.setPrimaryParent(parent1);
        child.setParent2(parent2);
        child = childService.saveChild(child);

        // Check if the values have changed since the child was saved.
        Child other = childService.loadChild(child.getId());
        assertTrue(child.equals(other));


        //test to get child by parent
        Collection<Child> children = childService.getChildrenByParentUsername(parent1.getUsername());
        assertNotNull(children);



        parent1.getChildren().remove(child);
        parent2.getChildren().remove(child);
        parentService.updateParent(parent1,child);
        parentService.updateParent(parent2,child);


        // Delete the child
        childService.deleteChild(child);

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
        sibling1 = InitializeSibling.initialize1();
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
        child.addSibling(sibling1);

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
        assertNotNull(child.toString());
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

        Collection<Child> childCollection = childService.getAllChildren();
        assertNotNull(childCollection);





    }


    @After
    public void cleanUp() {
        child = null;
        parent1 = null;
        parent2 = null;
        sibling1 = null;
    }
}
