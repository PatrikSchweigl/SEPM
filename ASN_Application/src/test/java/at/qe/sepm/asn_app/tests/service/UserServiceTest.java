package at.qe.sepm.asn_app.tests.service;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.services.UserService;
import at.qe.sepm.asn_app.tests.initialize.InitializeUserData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 18.06.17 17:29 CEST.
 */
@Component
@Scope("view")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    private UserData userData;


    @Before
    public void initialize() {
        userData = InitializeUserData.initialize1();
    }


    @DirtiesContext
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void testSaveAndDelete() {
        // Save the userData in the database.
        userData = userService.saveUser(userData);

        // Check if the values have changed since the userData was saved.
        UserData other = userService.loadUser(userData.getUsername());
        assertTrue(userData.equals(other));

        // Delete the userData again.
        userService.deleteUser(userData);
        other = userService.loadUser(userData.getUsername());
        assertFalse(userData.equals(other));
        assertNull(other);
    }

    @Test
    public void testFurtherMethods() {
        // Test toString()
        assertNotNull(userData.toString());
        assertNotEquals("", userData.toString());
    }


    @After
    public void cleanUp() {
        userData = null;
    }
}
