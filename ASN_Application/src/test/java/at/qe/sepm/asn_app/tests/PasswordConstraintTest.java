package at.qe.sepm.asn_app.tests;

import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.ownExceptions.PasswordConstraintException;
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


    /**
     * Password too short.
     */
    @Test
    public void checkPasswordComplexity1() {
        password = "1234567";
        assertFalse(PasswordConstraints.checkPasswordComplexity(password));
    }


    /**
     * Test a valid password.
     */
    @Test
    public void checkPasswordComplexity2() {
        password = "aB1+test";
        assertTrue(PasswordConstraints.checkPasswordComplexity(password));
    }


    @After
    public void cleanUp() {
        password = null;
        parent = null;
    }
}
