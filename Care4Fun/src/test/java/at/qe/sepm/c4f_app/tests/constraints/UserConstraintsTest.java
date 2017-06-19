package at.qe.sepm.c4f_app.tests.constraints;

import at.qe.sepm.c4f_app.models.UserData;
import at.qe.sepm.c4f_app.services.UserService;
import at.qe.sepm.c4f_app.tests.initialize.InitializeUserData;
import at.qe.sepm.c4f_app.ui.constraints.UserConstraints;
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 18.06.17 19:20 CEST.
 */
@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserConstraintsTest {

    @Autowired
    private UserConstraints userConstraints;
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
    public void testCheckIfUserNameExists() {
        assertFalse(userConstraints.checkIfUsernameExists(userData.getUsername()));
        userData = userService.saveUser(userData);
        assertTrue(userConstraints.checkIfUsernameExists(userData.getUsername()));
    }


    @After
    public void cleanUp() {
        userData = null;
    }
}
