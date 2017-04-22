package at.qe.sepm.asn_app.tests.views.children;


import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.child.Sibling;
import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.models.ownExceptions.BirthdayConstraintException;
import at.qe.sepm.asn_app.models.ownExceptions.SiblingConstraintException;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.services.ChildService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by root on 20.04.17.
 */
public class childServiceTest {

    private ChildService childService;
    private Child child1;
    private Child child2;
    private Child child3;
    private Child child4;
    private Child child5;
    private ArrayList<Child> listChildren;
    private Sibling sibling1;
    private Sibling sibling2;
    private Sibling sibling3;
    private Parent parent1;
    private Parent parent2;
    private Parent parent3;
    private Parent parent4;
    private Parent parent5;


    @Before
    public void initialize() {
        this.childService = new ChildService();
        this.listChildren = new ArrayList<>();

        // Having a '0' in front of the month could maybe be a problem because usually a 0 in front of a number means oct-numbers
        listChildren.add(child1 = new Child("FirstName1", "LastName1", "03/05/2015", Religion.BUDDHISM));
        listChildren.add(child2 = new Child("FirstName2", "LastName2", "04/04/2014", Religion.ATHEISM));     // Too old
        listChildren.add(child3 = new Child("FirstName1", "LastName1", "03/05/2015", Religion.BUDDHISM));
        listChildren.add(child4 = new Child("FirstName4", "LastName4", "21/02/2017", Religion.JUDAISM));    // Too young
        listChildren.add(child5 = new Child("FirstName5", "LastName5", "03/05/2015", Religion.HINDUISM));

        sibling1 = new Sibling(child1.getFirstName(), child1.getLastName(), child1.getBirthday());
        sibling2 = new Sibling(child2.getFirstName(), child2.getLastName(), child2.getBirthday());
        sibling3 = new Sibling(child3.getFirstName(), child3.getLastName(), child3.getBirthday());
    }


    /**
     * Test birthdayConstraints for no violations.
     * @throws BirthdayConstraintException
     */
    @Test
    public void checkBirthdayConstraints1() throws BirthdayConstraintException {
        childService.setChild(child1);
        assertTrue(childService.checkBirthdayConstraints());
    }


    /**
     * Test the birthday constraints for a child that is too young.
     * @throws BirthdayConstraintException
     */
    @Test (expected = BirthdayConstraintException.class)
    public void checkBirthdayConstraints2() throws BirthdayConstraintException {
        childService.setChild(child4);
        childService.checkBirthdayConstraints();
    }


    /**
     * Test the birthday constraints for a child that is too old.
     * @throws BirthdayConstraintException
     */
    @Test (expected = BirthdayConstraintException.class)
    public void checkBirthdayConstraints3() throws BirthdayConstraintException {
        this.childService.setChild(child2);
        this.childService.checkBirthdayConstraints();
    }


    /**
     * Test siblingsConstraints for no violation
     * @throws SiblingConstraintException
     */
    @Test
    public void checkSiblingsConstraints1() throws SiblingConstraintException {
        childService.setChild(child1);
        Set<Sibling> siblingSet = new HashSet<>();
        siblingSet.add(sibling2);
        childService.getChild().setListSiblings(siblingSet);
        assertTrue(childService.checkSiblingsConstraints());
    }


    @Test (expected = SiblingConstraintException.class)
    public void checkSiblingsConstraints2() throws SiblingConstraintException {
        childService.setChild(child1);
        Set<Sibling> siblingSet = new HashSet<>();
        siblingSet.add(sibling3);
        childService.getChild().setListSiblings(siblingSet);
    }

    @After
    public void cleanUp() {
        this.childService = null;
        for (Child c : this.listChildren) {
            c = null;
        }
        this.listChildren = null;
    }
}
