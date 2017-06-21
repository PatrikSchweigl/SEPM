package at.qe.sepm.asn_app.models;

import java.util.Objects;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import at.qe.sepm.asn_app.models.general.Religion;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.Persistable;

/**
 * Entity representing users.
 * UserData holds the most common basic attributes about a person: the username,
 * a password, the full name, the place they live in, the birthday and a UserRole
 * which specifies if a person is an admin, an employee or a parent.
 * A lot of classes make use of UserData, among others Employee and Parent
 * which both inherit from UserData.
 *
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
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String location;
    @NotNull
    private String streetName;
    @NotNull
    private String postcode;
    private String birthday;
    @NotNull
    private String email;
    private String imgName;


    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    private Religion religion;
    private String phoneNumber;

    @ColumnDefault("false")
    private boolean notification;

    // TODO doesn't work, write method which gives the number of same usernames instead of a global counter.
    //@Transient
    private static int usernameCounter = 1;

    public UserData() {
    }


    public UserData(String username, String password, String firstName, String lastName,
                    String location, String streetName, String postcode, String birthday,
                    String email, String imgName, UserRole userRole, Religion religion,
                    String phoneNumber, boolean notification) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.streetName = streetName;
        this.postcode = postcode;
        this.birthday = birthday;
        this.email = email;
        this.imgName = imgName;
        this.userRole = userRole;
        this.religion = religion;
        this.phoneNumber = phoneNumber;
        this.notification = notification;
    }

    // TODO The username gets still set ont he views. It should be auto generated though.
    public UserData(String username, String password, String firstName, String lastName,
                    String location, String streetName, String postcode, String birthday,
                    String email, UserRole userRole) {
        this.username = firstName + usernameCounter;
        usernameCounter++;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.streetName = streetName;
        this.postcode = postcode;
        this.birthday = birthday;
        this.email = email;
        this.userRole = userRole;
    }

    /**
     * Full constructor
     */
    public UserData(String username, String password, String firstName, String lastName,
                    String location, String streetName, String postcode, String birthday,
                    String email, String imgName, UserRole userRole, Religion religion, String phoneNumber) {
        this.username = firstName + usernameCounter;
        usernameCounter++;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.streetName = streetName;
        this.postcode = postcode;
        this.birthday = birthday;
        this.email = email;
        this.imgName = imgName;
        this.userRole = userRole;
        this.religion = religion;
        this.phoneNumber = phoneNumber;
    }


    public Religion getReligion() {
        return religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public boolean isNotification() {
        return notification;
    }


    public void setNotification(boolean notification) {
        this.notification = notification;
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
        } else if (!(obj instanceof UserData)) {
            return false;
        }

        UserData other = (UserData) obj;
        if (birthday.equals(other.birthday) &&
                firstName.equals(other.firstName) &&
                lastName.equals(other.lastName) &&
                location.equals(other.location) &&
                postcode.equals(other.postcode) &&
                streetName.equals(other.streetName) &&
                username.equals(other.username) &&
                userRole.equals(other.userRole)) {
            return true;
        } else {
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
