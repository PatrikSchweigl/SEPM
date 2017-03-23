package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.services.ParentService;
import at.qe.sepm.asn_app.services.UserService;
import at.qe.sepm.asn_app.models.User;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controller for the user list view.
 *
 * @author Michael Brunner <Michael.Brunner@uibk.ac.at>
 */
@Component
@Scope("view")
public class UserListController {


    @Autowired
    private ParentService parentService;

    /**
     * Returns a list of all users.
     *
     * @return
     */
    public Collection<Parent> getUsers() {

        return parentService.getAllParents();
    }

}
