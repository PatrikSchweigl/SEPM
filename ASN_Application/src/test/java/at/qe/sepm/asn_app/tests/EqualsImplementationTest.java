package at.qe.sepm.asn_app.tests;

import at.qe.sepm.asn_app.models.User;
import at.qe.sepm.asn_app.models.UserRole;
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
        User user1 = new User();
        user1.setUsername("user2");
        User user2 = new User();
        user2.setUsername("user2");
        EqualsVerifier.forClass(User.class).withPrefabValues(User.class, user1, user2).suppress(Warning.STRICT_INHERITANCE, Warning.ALL_FIELDS_SHOULD_BE_USED).verify();
    }

    @Test
    public void testUserRoleEqualsContract() {
        EqualsVerifier.forClass(UserRole.class).verify();
    }

}
