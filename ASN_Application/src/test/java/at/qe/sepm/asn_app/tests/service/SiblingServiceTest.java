package at.qe.sepm.asn_app.tests.service;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.child.Sibling;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.services.ChildService;
import at.qe.sepm.asn_app.services.ParentService;
import at.qe.sepm.asn_app.services.SiblingService;
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
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 17.06.17 17:10 CEST.
 */
@Component
@Scope("view")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SiblingServiceTest {

    @Autowired
    private ChildService childService;
    @Autowired
    private ParentService parentService;
    @Autowired
    private SiblingService siblingService;
    private Child child;
    private Parent parent1;
    private Parent parent2;
    private Sibling sibling;


    @Before
    public void initialize() {
        child = InitializeChild.initialize();
        parent1 = InitializeParent.initialize1();
        parent2 = InitializeParent.initialize2();
        sibling = InitializeSibling.initialize1();
        sibling.setChild(child);
    }



    @Test
    public void testSaveAndDelete() {
        // Save the parents in the database.
        parent1 = parentService.saveParent(parent1);
        parent2 = parentService.saveParent(parent2);

        // Save the child in the database.
        child.setPrimaryParent(parent1);
        child.setParent2(parent2);
        child = childService.saveChild(child);

        // Save the sibling in the database.
        // setChild is needed because otherwise sql cannot reference to the sibling!
        // It has to be set after child was saved.
        sibling.setChild(child);
        sibling = siblingService.saveSibling(sibling);
        System.out.println("SIBLIIIIIIIIIIING: " + sibling +
                            sibling.getId() + sibling.getChild().getId());

        // Check if the values have changed since the sibling was saved.
        Sibling other = siblingService.loadSibling(sibling.getId());
        assertTrue(sibling.equals(other));

        // Delete the sibling again.
        siblingService.deleteSibling(sibling);
        other = siblingService.loadSibling(sibling.getId());
        assertFalse(sibling.equals(other));
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
        String birthday = "11/11/2015";
        String firstName = "SiblingFirstName";
        String lastName = "SiblingLastName";

        // Sett attributes
        sibling = new Sibling();
        sibling.setBirthday(birthday);
        sibling.setChild(child);
        sibling.setFirstName(firstName);
        sibling.setLastName(lastName);

        // Compare all attributes with getters.
        assertEquals(birthday, sibling.getBirthday());
        assertEquals(child, sibling.getChild());
        assertEquals(firstName, sibling.getFirstName());
        assertEquals(lastName, sibling.getLastName());
    }


    @Test
    public void testFurtherMethods() {
        // Print all information with toString();
        assertNotEquals("", sibling.toString());
        System.out.println(sibling.toString());

        // Test isNew()
        assertFalse(sibling.isNew());

        sibling = new Sibling();
        sibling.setFirstName("SiblingIsNewFirstName");
        assertFalse(sibling.isNew());

        sibling = new Sibling();
        sibling.setLastName("SiblingIsNewLastName");
        assertFalse(sibling.isNew());

        sibling = new Sibling();
        assertTrue(sibling.isNew());
    }



    @After
    public void cleanUp() {
        child = null;
        parent1 = null;
        parent1 = null;
    }
}
