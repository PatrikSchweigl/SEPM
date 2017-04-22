package at.qe.sepm.asn_app.models;

import java.util.Objects;
import javax.persistence.*;

import org.springframework.data.domain.Persistable;

/**
 * Entity representing users.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class UserData implements Persistable<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String password;
    private String username;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public UserData(String password, String username, String firstName, String lastName, UserRole userRole) {
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
    }

    public UserData(){

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole role) {
        this.userRole = role;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof UserData)) {
            return false;
        }
        final UserData other = (UserData) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserData[ id=" + username + " ]";
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isNew() {
        return (username.equals(""));
    }

}
