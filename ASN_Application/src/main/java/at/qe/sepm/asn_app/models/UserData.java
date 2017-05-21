package at.qe.sepm.asn_app.models;

import java.util.Objects;
import javax.persistence.*;

import org.springframework.data.domain.Persistable;

/**
 * Entity representing users.
 * UserData holds the most common basic attributes about a person: the username,
 * a password, the full name, the place they live in, the birthday and a UserRole
 * which specifies if a person is an admin, an employee or a parent.
 * A lot of classes make use of UserData, among others Employee and Parent
 * which both inherit from UserData.
 * @see at.qe.sepm.asn_app.models.employee.Employee
 * @see at.qe.sepm.asn_app.models.referencePerson.Parent
 * @see at.qe.sepm.asn_app.models.nursery.Picture
 * @see at.qe.sepm.asn_app.models.nursery.Task
 * @see UserRole
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class UserData implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @Id
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String location;
    private String streetName;
    private String postcode;
    private String birthday;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;


    public UserData(){}

    public UserData(String password, String username, String firstName,
                    String lastName, String location, String streetName,
                    String postcode, UserRole userRole, String birthday) {
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.streetName = streetName;
        this.postcode = postcode;
        this.userRole = userRole;
        this.birthday = birthday;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.username);
        return hash;
    }


    @Override
    public String getId() {
    	return username;
    }


    @Override
    public boolean isNew() {
        return (username.equals(""));
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        else if (!(obj instanceof UserData)) {
            return false;
        }

        UserData other = (UserData) obj;
        if (this.birthday.equals(other.birthday) &&
                this.firstName.equals(other.firstName) &&
                this.lastName.equals(other.lastName) &&
                this.location.equals(other.location) &&
                this.postcode.equals(other.postcode) &&
                this.streetName.equals(other.streetName) &&
                this.username.equals(other.username) &&
                this.userRole.equals(other.userRole)) {
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public String toString() {
        return "Username: " + username + "\n" +
                "FirstName: " + firstName + "\n" +
                "LastName: " + lastName + "\n" +
                "Birthday: " + birthday + "\n" +
                "UserRole: " + userRole + "\n" +
                "Postcode: " + postcode + "\n" +
                "Location: " + location + "\n" +
                "StreetName: " + streetName;
    }
}
