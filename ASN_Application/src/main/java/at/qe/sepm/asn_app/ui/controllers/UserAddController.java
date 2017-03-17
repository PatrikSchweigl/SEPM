package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.model.User;
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

    private User user;


    @PostConstruct
    public void initNewUser() {
        this.user = new User();
    }

    public User getUser() {
        return user;
    }

    public void doSaveUser() {
        user = this.userService.saveUser(user);
        initNewUser();
    }

}
