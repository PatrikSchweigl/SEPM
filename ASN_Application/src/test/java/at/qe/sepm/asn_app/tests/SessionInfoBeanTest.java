package at.qe.sepm.asn_app.tests;

import at.qe.sepm.asn_app.Main;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.services.UserService;
import at.qe.sepm.asn_app.ui.beans.SessionInfoBean;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Some very basic tests for {@link UserService}.
 *
 * @author Michael Brunner <Michael.Brunner@uibk.ac.at>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
@Component
@Scope("test")
@SpringBootTest
public class SessionInfoBeanTest {

    @Autowired
    SessionInfoBean sessionInfoBean;

    @Test
    @WithMockUser(username = "user1", authorities = {"EMPLOYEE"})
    public void testLoggedIn() {
        Assert.assertTrue("sessionInfoBean.isLoggedIn does not return true for authenticated user", sessionInfoBean.isLoggedIn());
        Assert.assertEquals("sessionInfoBean.getCurrentUserName does not return authenticated user's name", "user1", sessionInfoBean.getCurrentUserName());
        Assert.assertEquals("sessionInfoBean.getCurrentUserData does not return authenticated user", "user1", sessionInfoBean.getCurrentUserData().getUsername());
        Assert.assertEquals("sessionInfoBean.getCurrentUserRoles does not return authenticated user's roles", "EMPLOYEE", sessionInfoBean.getCurrentUserRoles());
        Assert.assertTrue("sessionInfoBean.hasRole does not return true for a role the authenticated user has", sessionInfoBean.hasRole("EMPLOYEE"));
        Assert.assertFalse("sessionInfoBean.hasRole does not return false for a role the authenticated user does not have", sessionInfoBean.hasRole("ADMIN"));
    }

    @Test
    public void testNotLoggedIn() {
        Assert.assertFalse("sessionInfoBean.isLoggedIn does return true for not authenticated user", sessionInfoBean.isLoggedIn());
        Assert.assertEquals("sessionInfoBean.getCurrentUserName does not return empty string when not logged in", "", sessionInfoBean.getCurrentUserName());
        Assert.assertNull("sessionInfoBean.getCurrentUserData does not return null when not logged in", sessionInfoBean.getCurrentUserData());
        Assert.assertEquals("sessionInfoBean.getCurrentUserRoles does not return empty string when not logged in", "", sessionInfoBean.getCurrentUserRoles());
        for (UserRole role : UserRole.values()) {
            Assert.assertFalse("sessionInfoBean.hasRole does not return false for all possible roales", sessionInfoBean.hasRole(role.name()));
        }
    }

}
