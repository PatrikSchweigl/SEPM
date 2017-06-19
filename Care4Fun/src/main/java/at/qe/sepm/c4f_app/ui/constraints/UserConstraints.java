package at.qe.sepm.c4f_app.ui.constraints;

import at.qe.sepm.c4f_app.models.UserData;
import at.qe.sepm.c4f_app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 07.06.2017
 */

@Component
@Scope("application")
public class UserConstraints {

    @Autowired
    private UserService userService;

    public boolean checkIfUsernameExists(String username){
        UserData userData = userService.loadUser(username);
        return userData != null;
    }
}
