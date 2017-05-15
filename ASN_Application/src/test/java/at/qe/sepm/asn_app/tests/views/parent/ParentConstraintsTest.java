package at.qe.sepm.asn_app.tests.views.parent;

import at.qe.sepm.asn_app.models.Gender;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.child.Sibling;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.models.referencePerson.Assignment;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.ownExceptions.BirthdayConstraintException;
import at.qe.sepm.asn_app.ownExceptions.ParentConstraintException;
import at.qe.sepm.asn_app.services.ParentService;
import at.qe.sepm.asn_app.ui.constraints.ParentConstraints;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by zerus on 08.05.17.
 */
public class ParentConstraintsTest {

    private Child child1;
    private Child child2;
    private Child child3;
    private Child child4;
    private Child child5;
    private ArrayList<Child> listChildren;
    private Sibling sibling1;
    private Sibling sibling2;
    private Sibling sibling3;
    private ArrayList<Sibling> listSiblings;
    private Parent parent1;
    private Parent parent2;
    private Parent parent3;
    private Parent parent4;
    private ArrayList<Parent> listParents;
    private ParentConstraints parentConstraints;
    private ParentService parentService;

    /**
     * Initialize every attribute with static values.
     */
    @Before
    public void initialize() {
        Set<Child> ParentListChildren1 = new HashSet<>();
        Set<Child> ParentListChildren2 = new HashSet<>();
        Set<Child> ParentListChildren3 = new HashSet<>();
        Set<Assignment> ParentListAssignments1 = new HashSet<>();
        Set<Assignment> ParentListAssignments2 = new HashSet<>();
        Set<Assignment> ParentListAssignments3 = new HashSet<>();

        // Having a '0' in front of the month could maybe be a problem because usually a 0 in front of a number means oct-numbers
        listParents = new ArrayList<>();
        listParents.add(parent1 = new Parent("", "ParentUserName1", "ParentFirstName1", "ParentLastName1", "ParentLocation1", "ParentStreetName1", "ParentPostcode1", UserRole.PARENT, "ParentImgName1", ParentListChildren1, ParentListAssignments1, FamilyStatus.MARRIED, true, "24/05/1980"));
        listParents.add(parent2 = new Parent("", "ParentUserName2", "ParentFirstName2", "ParentLastName2", "ParentLocation2", "ParentStreetName2", "ParentPostcode2", UserRole.PARENT, "ParentImgName2", ParentListChildren2, ParentListAssignments2, FamilyStatus.DIVORCED, true, "11/11/2003"));  // Too young
        listParents.add(parent3 = new Parent("", "ParentUserName3", "ParentFirstName3", "ParentLastName3", "ParentLocation3", "ParentStreetName3", "ParentPostcode3", UserRole.PARENT, "ParentImgName3", ParentListChildren3, ParentListAssignments3, FamilyStatus.NOT_MARRIED, true, "30/04/1918"));   // Too old
        listParents.add(parent4 = new Parent("", "ParentUserName3", "ParentFirstName3", "ParentLastName3", "ParentLocation3", "ParentStreetName3", "ParentPostcode3", UserRole.PARENT, "ParentImgName3", ParentListChildren3, ParentListAssignments3, FamilyStatus.NOT_MARRIED, true, "30/04/1918"));   // Same as parent3
        parentService = new ParentService();

        listChildren = new ArrayList<>();
        listChildren.add(child1 = new Child("FirstName1", "LastName1", "03/05/2015", "ImageName1", Gender.MALE, parent1));
        listChildren.add(child2 = new Child("FirstName2", "LastName2", "04/04/2014", "ImageName2", Gender.FEMALE, parent1));     // Too old
        listChildren.add(child3 = new Child("FirstName1", "LastName1", "03/05/2015", "ImageName3", Gender.MALE, parent2));
        listChildren.add(child4 = new Child("FirstName4", "LastName4", "21/02/2017", "ImageName4", Gender.FEMALE, parent2));    // Too young
        listChildren.add(child5 = new Child("FirstName5", "LastName5", "03/05/2015", "ImageName5", Gender.MALE, parent3));

        listSiblings = new ArrayList<>();
        listSiblings.add(sibling1 = new Sibling(child1.getFirstName(), child1.getLastName(), child1.getBirthday()));
        listSiblings.add(sibling2 = new Sibling(child2.getFirstName(), child2.getLastName(), child2.getBirthday()));
        listSiblings.add(sibling3 = new Sibling(child3.getFirstName(), child3.getLastName(), child3.getBirthday()));
    }


    /**
     * No constraint violation
     */
    @Test
    public void checkBirthdayConstraintsTest1() throws BirthdayConstraintException {
        ParentConstraints.checkBirthdayConstraints(parent1);
    }


    /**
     * Check for the violation of the constraint that a parent must not be younger than 14 years.
     * @throws BirthdayConstraintException
     */
    @Test
    public void checkBirthdayConstraintsTest2() throws BirthdayConstraintException {
        assertFalse(ParentConstraints.checkBirthdayConstraints(parent2));
    }


    /**
     * Check for the violation of the constraint that a parent must not be older than 99 years.
     * @throws BirthdayConstraintException
     */
    @Test
    public void checkBirthdayConstraintsTest3() throws BirthdayConstraintException {
        assertFalse(ParentConstraints.checkBirthdayConstraints(parent3));
    }


    // TODO the parent repository in parentService is null somehow.
    @Test
    public void alreadyExistsTest() {
        parentService.saveParent(parent3);
        assertTrue(ParentConstraints.alreadyExists(parent4));
    }


    /**
     * Set every attribute to null so it is assured that every test works with clean attributes.
     */
    @After
    public void cleanUp() {
        for (Child c : listChildren) {
            c = null;
        }
        listChildren = null;

        for (Sibling s : listSiblings) {
            s = null;
        }
        listSiblings = null;

        for (Parent p : listParents) {
            p = null;
        }
        listParents = null;

        parentConstraints = null;
        parentService = null;
    }

}
