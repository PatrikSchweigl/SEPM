package at.qe.sepm.asn_app.ui.constraints;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.services.UserService;
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
        try{
            UserData userData = userService.loadUser(username);
            System.out.println(userData.getFirstName()+" "+userData.getLastName()+"-----------------------------------");
        }catch (Exception ex){
            return true;
        }finally {
            return false;
        }

    }
}
