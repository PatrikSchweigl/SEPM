package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;

/**
 * Controller for the userData detail view.
 *
 * @author Michael Brunner <Michael.Brunner@uibk.ac.at>
 */
@Component
@Scope("application")
public class UserController {

    @Autowired
    private UserService userService;

    private String email;

    /**
     * Attribute to cache the currently displayed userData
     */
    private UserData userData;
    private Collection<UserData> users;

    @PostConstruct
    public void initList() {
        setUsers(userService.getAllAdmin());
    }

    /**
     * Returns a list of all users.
     *
     * @return
     */
    public Collection<UserData> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserData> users) {
        this.users = users;
    }

    /**
     * Sets the currently displayed userData and reloads it form db. This userData is
     * targeted by any further calls of
     * {@link #doReloadUser()}, {@link #doSaveUser()} and
     * {@link #doDeleteUser()}.
     *
     * @param userData
     */
    public void setUserData(UserData userData) {
        this.userData = userData;
        doReloadUser();
    }

    /**
     * Returns the currently displayed userData.
     *
     * @return
     */
    public UserData getUserData() {
        return userData;
    }

    public void generatePassword() {
        userService.generatePassword(email);
    }


    /**
     * Action to force a reload of the currently displayed userData.
     */
    public void doReloadUser() {
        userData = userService.loadUser(userData.getUsername());
    }

    /**
     * Action to delete the currently displayed userData.
     */
    public void doDeleteUser() {
        this.userService.deleteUser(userData);
        userData = null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
