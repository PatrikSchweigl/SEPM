package at.qe.sepm.asn_app.tests.views.children;


import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.child.Sibling;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.models.referencePerson.Assignment;
import at.qe.sepm.asn_app.ownExceptions.BirthdayConstraintException;
import at.qe.sepm.asn_app.ownExceptions.ParentConstraintException;
import at.qe.sepm.asn_app.ownExceptions.SiblingConstraintException;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.services.ChildService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by Bernd Menia on 20.04.17.
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

    // TODO Check if a parent exists in the nursery
    // TODO check naming constraints (e.g numbers etc. )

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

        /*
        public Parent(String password, String username, String firstName, String lastName,
                  String imgName, String location, String postcode, String streetName, Set<Child> listChildren,
                  Set<Assignment> listAssignments, FamilyStatus familyStatus, boolean status)
         */
        Set<Child> ParentListChildren1 = new HashSet<>();
        Set<Child> ParentListChildren2 = new HashSet<>();
        Set<Child> ParentListChildren3 = new HashSet<>();
        Set<Assignment> ParentListAssignments1 = new HashSet<>();
        Set<Assignment> ParentListAssignments2 = new HashSet<>();
        Set<Assignment> ParentListAssignments3 = new HashSet<>();
        parent1 = new Parent("", "ParentUserName1", "ParentFirstName1", "ParentLastName1", "ParentLocation1", "ParentStreetName1", "ParentPostcode1", UserRole.PARENT, "ParentImgName1", ParentListChildren1, ParentListAssignments1, FamilyStatus.MARRIED, true);
        parent1 = new Parent("", "ParentUserName2", "ParentFirstName2", "ParentLastName2", "ParentLocation2", "ParentStreetName2", "ParentPostcode2", UserRole.PARENT, "ParentImgName2", ParentListChildren2, ParentListAssignments2, FamilyStatus.DIVORCED, true);
        parent1 = new Parent("", "ParentUserName3", "ParentFirstName3", "ParentLastName3", "ParentLocation3", "ParentStreetName3", "ParentPostcode3", UserRole.PARENT, "ParentImgName3", ParentListChildren3, ParentListAssignments3, FamilyStatus.NOT_MARRIED, true);

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
    @Test(expected = BirthdayConstraintException.class)
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
        childService.setChild(child2);
        childService.checkBirthdayConstraints();
    }


    /**
     * Test parents constraints without any violation.
     */
    @Test
    public void checkParentsConstraints1() throws ParentConstraintException {
        childService.setChild(child1);
        child1.setParent1(parent1);
        child1.setParent2(parent2);
        childService.checkParentsConstraints();
    }


    /**
     * It is not allowed to register children in the nursery if not at least one parent is registered beforehand.
     * @throws ParentConstraintException
     */
    @Test (expected = ParentConstraintException.class)
    public void checkParentsConstraints2() throws ParentConstraintException {
        childService.setChild(child1);
        childService.checkParentsConstraints();
    }


    /**
     * Check the violation of the constraint that a child may not have the same parent twice.
     * @throws ParentConstraintException
     */
    @Test (expected = ParentConstraintException.class)
    public void checkParentsConstraints3() throws ParentConstraintException {
        childService.setChild(child1);
        child1.setParent1(parent1);
        child1.setParent2(parent1);
        childService.checkParentsConstraints();
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


    /**
     * Test the violation of the constraint that a child can't be a sibling of itself.
     * @throws SiblingConstraintException
     */
    @Test (expected = SiblingConstraintException.class)
    public void checkSiblingsConstraints2() throws SiblingConstraintException {
        childService.setChild(child1);
        Set<Sibling> siblingSet = new HashSet<>();
        siblingSet.add(sibling3);
        childService.getChild().setListSiblings(siblingSet);
        childService.checkSiblingsConstraints();
    }


    /**
     * Test the violation of the constraint that a child can't have the same sibling twice or more.
     * @throws SiblingConstraintException
     */
    @Test (expected = SiblingConstraintException.class)
    public void checkSiblingsConstraints3() throws SiblingConstraintException {
        childService.setChild(child5);
        Set<Sibling> siblingSet = new HashSet<>();
        siblingSet.add(sibling1);
        siblingSet.add(sibling3);
        childService.getChild().setListSiblings(siblingSet);
        childService.checkSiblingsConstraints();
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
