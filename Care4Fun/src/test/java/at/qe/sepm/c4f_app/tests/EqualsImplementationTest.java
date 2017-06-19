package at.qe.sepm.c4f_app.tests;

import at.qe.sepm.c4f_app.models.UserData;
import at.qe.sepm.c4f_app.models.UserRole;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

/**
 * Tests to ensure that each entity's implementation of equals conforms to the
 * contract. See {@linkplain http://www.jqno.nl/equalsverifier/} for more
 * information.
 *
 * @author Michael Brunner <Michael.Brunner@uibk.ac.at>
 */
public class EqualsImplementationTest {

    @Test
    public void testUserEqualsContract() {
        UserData userData1 = new UserData();
        userData1.setUsername("userData2");
        UserData userData2 = new UserData();
        userData2.setUsername("userData2");
        EqualsVerifier.forClass(UserData.class).withPrefabValues(UserData.class, userData1, userData2).suppress(Warning.STRICT_INHERITANCE, Warning.ALL_FIELDS_SHOULD_BE_USED).verify();
    }

    @Test
    public void testUserRoleEqualsContract() {
        EqualsVerifier.forClass(UserRole.class).verify();
    }

}
