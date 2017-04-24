package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 17.03.2017
 */

@Component
@Scope("view")
public class UserAddController {

    @Autowired
    private UserService userService;

    private UserData userData;


    @PostConstruct
    public void initNewUser() {
        this.userData = new UserData();
    }

    public UserData getUserData() {
        return userData;
    }

    public void doSaveUser() {
        userData = userService.saveUser(userData);
        userData = null;
        initNewUser();
    }

}
