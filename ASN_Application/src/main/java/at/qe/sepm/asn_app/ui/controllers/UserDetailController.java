package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.services.UserService;
import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.ui.beans.SessionInfoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controller for the userData detail view.
 *
 * @author Michael Brunner <Michael.Brunner@uibk.ac.at>
 */
@Component
@Scope("view")
public class UserDetailController {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionInfoBean sessionInfoBean;

    /**
     * Attribute to cache the currently displayed userData
     */
    private UserData userData;

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


    public UserData getCurrentData(){
        setUserData(sessionInfoBean.getCurrentUserData());
        return userData;
    }

    /**
     * Action to force a reload of the currently displayed userData.
     */
    public void doReloadUser() {
        userData = userService.loadUser(userData.getUsername());
    }

    /**
     * Action to save the currently displayed userData.
     */
    public void doSaveUser() {
        userData = this.userService.saveUser(userData);
    }

    public void doChangeUser() {
        userData = this.userService.changeData(userData);
    }

    /**
     * Action to delete the currently displayed userData.
     */
    public void doDeleteUser() {
        this.userService.deleteUser(userData);
        userData = null;
    }

}
