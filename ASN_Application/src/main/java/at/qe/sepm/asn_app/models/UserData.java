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
    private Long id;
    private String password;
    private String username;
    private String firstName;
    private String lastName;
    private String location;
    private String streetName;
    private String postcode;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public UserData(String password, String username, String firstName,
                    String lastName, String location, String streetName,
                    String postcode, UserRole userRole) {
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.streetName = streetName;
        this.postcode = postcode;
        this.userRole = userRole;
    }

    public UserData(){

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
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
    	return id;
    }

    @Override
    public boolean isNew() {
        return (username.equals(""));
    }

}
