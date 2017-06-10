package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.services.UserService;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Controller for the user list view.
 *
 * @author Michael Brunner <Michael.Brunner@uibk.ac.at>
 */
@Component
@Scope("view")
public class UserListController {


    @Autowired
    private UserService userService;
    
    private Collection<UserData> admins;
    
    
    @PostConstruct
    public void initList(){
        setUsers(userService.getAllAdmin());
    }

    /**
     * Returns a list of all users.
     *
     * @return
     */
    public Collection<UserData> getUsers() {
        return admins;
    }
    
    public void setUsers(Collection<UserData> users) {
        this.admins = users;
    }
}
