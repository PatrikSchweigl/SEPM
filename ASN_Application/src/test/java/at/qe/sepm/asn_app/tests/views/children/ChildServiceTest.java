package at.qe.sepm.asn_app.tests.views.children;

import at.qe.sepm.asn_app.models.Gender;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.child.Sibling;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.nursery.Task;
import at.qe.sepm.asn_app.ownExceptions.BirthdayConstraintException;
import at.qe.sepm.asn_app.ownExceptions.ParentConstraintException;
import at.qe.sepm.asn_app.ownExceptions.SiblingConstraintException;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.ui.constraints.ChildConstraints;
import org.junit.Ignore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 20.04.17.
 *
 *
 */
public class ChildServiceTest {

    private Child child1;
    private Child child2;
    private Child child3;
    private Child child4;
    private Child child5;
    private Set<Child> children;

    private Sibling sibling1;
    private Sibling sibling2;
    private Sibling sibling3;
    private Set<Sibling> siblings;

    private Parent parent1;
    private Parent parent2;
    private Parent parent3;
    private Parent parent4;
    private Parent parent5;
    private Set<Parent> parents;

    // TODO Check if a parent exists in the nursery
    // TODO check naming constraints (e.g numbers etc. )

    /**
     * To make sure that every test works with the same starting values all generally
     * needed attributes get initialized before each test. After each test all attributes
     * get set to null again in {@link ChildServiceTest#cleanUp()}.
     */
    @Ignore
    @Before
    public void initialize() {

        // Having a '0' in front of the month could maybe be a problem because usually a 0 in front of a number means oct-numbers
        children = new HashSet<>();
        children.add(child1 = new Child("FirstName1", "LastName1", "03/05/2015", "ImageName1", Gender.MALE, parent1));
        children.add(child2 = new Child("FirstName2", "LastName2", "04/04/2014", "ImageName2", Gender.FEMALE, parent1));     // Too old
        children.add(child3 = new Child("FirstName1", "LastName1", "03/05/2015", "ImageName3", Gender.MALE, parent2));
        children.add(child4 = new Child("FirstName4", "LastName4", "21/02/2017", "ImageName4", Gender.FEMALE, parent2));    // Too young
        children.add(child5 = new Child("FirstName5", "LastName5", "03/05/2015", "ImageName5", Gender.MALE, parent3));

        siblings = new HashSet<>();
        sibling1 = new Sibling(child1.getFirstName(), child1.getLastName(), child1.getBirthday());
        sibling2 = new Sibling(child2.getFirstName(), child2.getLastName(), child2.getBirthday());
        sibling3 = new Sibling(child3.getFirstName(), child3.getLastName(), child3.getBirthday());

        // These variables are needed to initialize the parents.
        Set<Child> children1 = new HashSet<>();
        Set<Child> children2 = new HashSet<>();
        Set<Child> children3 = new HashSet<>();
        Set<Task> parentTasks1 = new HashSet<>();
        Set<Task> parentTasks2 = new HashSet<>();
        Set<Task> parentTasks3 = new HashSet<>();

        parents = new HashSet<>();
        // TODO The parent constructor is useless --> it is empty
        parents.add(parent1 = new Parent("", "ParentUserName1", "ParentFirstName1", "ParentLastName1", "ParentLocation1", "ParentStreetName1", "ParentPostcode1", UserRole.PARENT, "ParentImgName1", children1, parentTasks1, FamilyStatus.VERHEIRATET, true, "24/05/1980"));
        parents.add(parent2 = new Parent("", "ParentUserName2", "ParentFirstName2", "ParentLastName2", "ParentLocation2", "ParentStreetName2", "ParentPostcode2", UserRole.PARENT, "ParentImgName2", children2, parentTasks2, FamilyStatus.GESCHIEDEN, true, "11/11/2003"));  // Too young
        parents.add(parent3 = new Parent("", "ParentUserName3", "ParentFirstName3", "ParentLastName3", "ParentLocation3", "ParentStreetName3", "ParentPostcode3", UserRole.PARENT, "ParentImgName3", children3, parentTasks3, FamilyStatus.LEDIG, true, "30/04/1918"));   // Too old
        parents.add(parent4 = new Parent("", "ParentUserName3", "ParentFirstName3", "ParentLastName3", "ParentLocation3", "ParentStreetName3", "ParentPostcode3", UserRole.PARENT, "ParentImgName3", children3, parentTasks3, FamilyStatus.LEDIG, true, "30/04/1918"));   // Same as parent3
    }


    /**
     * Test birthdayConstraints for no violations.
     */
    @Ignore
    @Test
    public void checkBirthdayConstraints1() throws BirthdayConstraintException {
        assertTrue(ChildConstraints.checkBirthdayConstraints(child1));
    }


    /**
     * Test the birthday constraints for a child that is too young.
     */
    @Ignore
    @Test
    public void checkBirthdayConstraints2() throws BirthdayConstraintException {
        assertFalse(ChildConstraints.checkBirthdayConstraints(child4));
    }


    /**
     * Test the birthday constraints for a child that is too old.
     */
    @Ignore
    @Test
    public void checkBirthdayConstraints3() throws BirthdayConstraintException {
        assertFalse(ChildConstraints.checkBirthdayConstraints(child2));
    }


    /**
     * Test parents constraints without any violation.
     */
    @Ignore
    @Test
    public void checkParentsConstraints1() throws ParentConstraintException {
        //child1.setParent1(parent1);
        child1.setPrimaryParent(parent1);
        child1.setParent2(parent2);
        ChildConstraints.checkParentsConstraints(child1);
    }


    /**
     * It is not allowed to register children in the nursery if not at least one parent is registered beforehand.
     */
    @Ignore
    @Test
    public void checkParentsConstraints2() throws ParentConstraintException {
        assertFalse(ChildConstraints.checkParentsConstraints(child1));
    }


    /**
     * Check the violation of the constraint that a child may not have the same parent twice.
     */
    @Ignore
    @Test
    public void checkParentsConstraints3() throws ParentConstraintException {
        //child1.setParent1(parent1);
        child1.setPrimaryParent(parent1);
        child1.setParent2(parent1);
        assertFalse(ChildConstraints.checkParentsConstraints(child1));
    }


    /**
     * Test siblingsConstraints for no violation
     */
    @Ignore
    @Test
    public void checkSiblingsConstraints1() throws SiblingConstraintException {
        Set<Sibling> siblingSet = new HashSet<>();
        siblingSet.add(sibling2);
        child1.setSiblings(siblingSet);
        assertTrue(ChildConstraints.checkSiblingsConstraints(child1));
    }


    /**
     * Test the violation of the constraint that a child can't be a sibling of itself.
     */
    @Ignore
    @Test
    public void checkSiblingsConstraints2() throws SiblingConstraintException {
        Set<Sibling> siblingSet = new HashSet<>();
        siblingSet.add(sibling3);
        child1.setSiblings(siblingSet);
        assertFalse(ChildConstraints.checkSiblingsConstraints(child1));
    }


    /**
     * Test the violation of the constraint that a child can't have the same sibling twice or more.
     */
    @Ignore
    @Test
    public void checkSiblingsConstraints3() throws SiblingConstraintException {
        Set<Sibling> siblingSet = new HashSet<>();
        siblingSet.add(sibling1);
        siblingSet.add(sibling3);
        child5.setSiblings(siblingSet);
        assertFalse(ChildConstraints.checkSiblingsConstraints(child5));
    }

    @Ignore
    @After
    public void cleanUp() {
        for (Child child : children) {
            child = null;
        }
        children = null;

        for (Parent parent : parents) {
            parent = null;
        }
        parents = null;

        for (Sibling sibling : siblings) {
            sibling = null;
        }
        siblings = null;

    }
}