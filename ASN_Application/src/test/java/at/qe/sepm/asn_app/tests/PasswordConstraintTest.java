package at.qe.sepm.asn_app.tests;

import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.nursery.Task;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.ownExceptions.PasswordConstraintException;
import at.qe.sepm.asn_app.ui.constraints.PasswordConstraints;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;

/**
 * Created by zerus on 13.05.17.
 */
public class PasswordConstraintTest {

    private Child child;
    private String password;
    private Parent parent;
    Set<Child> parentListChildren;
    Set<Task> parentListTasks;


    @Before
    public void initialize() {
        child = new Child();
        child.setFirstName("Bob");
        child.setLastName("Dole");
        parentListChildren = new HashSet<>(Arrays.asList(child));
        parentListTasks = new HashSet<>();
        parent = new Parent("", "ParentUserName1", "ParentFirstName1", "ParentLastName1",
                "ParentLocation1", "ParentStreetName1", "ParentPostcode1", UserRole.PARENT,
                "ParentImgName1", parentListChildren, parentListTasks, FamilyStatus.MARRIED, true, "24/05/1980");
    }


    // TODO split the regex in PasswordConstraints up so that it is possible to check each constraint one by one.

    /**
     * Test a valid password.
     */
    @Test
    public void checkPasswordConstraints1() throws PasswordConstraintException {
        password = "aB1+test";
        PasswordConstraints.checkPasswordConstraints(password);
    }


    /**
     * A password has to contain at least one digit.
     */
    @Test
    public void checkPasswordComplexity1() {
        password = "aBB+test";
        assertFalse(PasswordConstraints.checkPasswordComplexity(password));
    }


    /**
     * A password has to contain at least one lower case character.
     */
    @Test
    public void checkPasswordComplexity2() {
        password = "AB1+TEST";
        assertFalse(PasswordConstraints.checkPasswordComplexity(password));
    }


    /**
     * A password has to contain at least one upper case character.
     */
    @Test
    public void checkPasswordComplexity3() {
        password = "ab1+test";
        assertFalse(PasswordConstraints.checkPasswordComplexity(password));
    }


    /**
     * A password has to contain at least one special character.
     */
    @Test
    public void checkPasswordComplexity4() {
        password = "aB11test";
        assertFalse(PasswordConstraints.checkPasswordComplexity(password));
    }


    /**
     * A password must not contain any whitespaces.
     */
    @Test
    public void checkPasswordComplexity5() {
        password = "aB1+ test";
        assertFalse(PasswordConstraints.checkPasswordComplexity(password));
    }


    /**
     * Password too short.
     */
    @Test
    public void checkPasswordComplexity6() {
        password = "aB1+tes";
        assertFalse(PasswordConstraints.checkPasswordComplexity(password));
    }


    /**
     * The name of a child is not allowed as a substring of the password of a parent.
     * Test the child name at the beginning of the parent password.
     */
    @Test
    public void checkPasswordChildNameTest1() {
        password = "bobaB1+test";
        assertFalse(PasswordConstraints.checkPasswordChildName(password, parent));
    }


    /**
     * Test the child name in the middle of the parent password.
     */
    @Test
    public void checkPasswordChildNameTest2() {
        password = "aB1+bobtest";
        assertFalse(PasswordConstraints.checkPasswordChildName(password, parent));
    }


    /**
     * Test the child name at the end of the parent password.
     */
    @Test
    public void checkPasswordChildNameTest3() {
        password = "aB1+testbob";
        assertFalse(PasswordConstraints.checkPasswordChildName(password, parent));
    }


    /**
     * Test the validity of the parent password if the child name is split up.
     */
    @Test
    public void checkPasswordChildName4() throws PasswordConstraintException {
        password="aB1+btoebst";
        PasswordConstraints.checkPasswordConstraintsParent(password, parent);
    }


    /**
     * Make sure that all attributes get set to null so that every test has clean values.
     */
    @After
    public void cleanUp() {
        child = null;
        password = null;
        parent = null;
        parentListChildren = null;
        parentListTasks = null;
    }
}