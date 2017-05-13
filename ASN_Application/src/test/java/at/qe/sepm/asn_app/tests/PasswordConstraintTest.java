package at.qe.sepm.asn_app.tests;

import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.ui.constraints.PasswordConstraints;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by zerus on 13.05.17.
 */
public class PasswordConstraintTest {

    private String password;
    private Parent parent;


    @Before
    public void initialize() {

    }


    // TODO split the regex in PasswordConstraints up so that it is possible to check each constraint one by one.

    /**
     * Test a valid password.
     */
    @Test
    public void checkPasswordComplexity1() {
        password = "aB1+test";
        assertTrue(PasswordConstraints.checkPasswordComplexity(password));
    }


    /**
     * A password has to contain at least one digit.
     */
    @Test
    public void checkPasswordComplexity2() {
        password = "aBB+test";
        assertFalse(PasswordConstraints.checkPasswordComplexity(password));
    }


    /**
     * A password has to contain at least one lower case character.
     */
    @Test
    public void checkPasswordComplexity3() {
        password = "AB1+TEST";
        assertFalse(PasswordConstraints.checkPasswordComplexity(password));
    }


    /**
     * A password has to contain at least one upper case character.
     */
    @Test
    public void checkPasswordComplexity4() {
        password = "ab1+test";
        assertFalse(PasswordConstraints.checkPasswordComplexity(password));
    }


    /**
     * A password has to contain at least one special character.
     */
    @Test
    public void checkPasswordComplexity5() {
        password = "aB11test";
        assertFalse(PasswordConstraints.checkPasswordComplexity(password));
    }

    /**
     * A password must not contain any whitespaces.
     */
    @Test
    public void checkPasswordComplexity6() {
        password = "aB1+ test";
        assertFalse(PasswordConstraints.checkPasswordComplexity(password));
    }


    /**
     * Password too short.
     */
    @Test
    public void checkPasswordComplexity7() {
        password = "aB1+tes";
        assertFalse(PasswordConstraints.checkPasswordComplexity(password));
    }


    /**
     * Make sure that all attributes get set to null so that every test has clean values.
     */
    @After
    public void cleanUp() {
        password = null;
        parent = null;
    }
}
