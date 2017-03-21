package at.qe.sepm.asn_app.models.admin;

import at.qe.sepm.asn_app.models.User;
import at.qe.sepm.asn_app.models.UserRole;
import org.springframework.boot.actuate.autoconfigure.ShellProperties;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by zerus on 17.03.17.
 */
@Entity
public class Admin extends User{
    private static final long serialVersionUID = 1L;




    public Admin(String firstName, String lastName, String username, String password) {

        super(password,username,firstName, lastName,UserRole.ADMIN);
    }


}
