package at.qe.sepm.asn_app.models.employee;

import at.qe.sepm.asn_app.models.User;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.general.Religion;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by zerus on 17.03.17.
 */
@Entity
public class Employee extends User{

    private static final long serialVersionUID = 1L;



    @NotNull
    private Date birthday;
    @NotNull
    private String location;
    private String streetName;
    @NotNull
    private String postcode;
    @Enumerated(EnumType.STRING)
    private Religion religion;
    @NotNull
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private FamilyStatus familyStatus;
    @Enumerated(EnumType.STRING)
    private Status workingState;
    @Enumerated(EnumType.STRING)
    private Role role;

    public Employee(String password, String username, String firstName, String lastName,
                    Date birthday, String location, String streetName, String postcode, Religion religion,
                    String phoneNumber, FamilyStatus familyStatus, Status workingState, Role role) {
        super(password, username, firstName, lastName, UserRole.ANGESTELLTER);
        this.birthday = birthday;
        this.location = location;
        this.streetName = streetName;
        this.postcode = postcode;
        this.religion = religion;
        this.phoneNumber = phoneNumber;
        this.familyStatus = familyStatus;
        this.workingState = workingState;
        this.role = role;
    }


    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
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

    public FamilyStatus getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(FamilyStatus familyStatus) {
        this.familyStatus = familyStatus;
    }

    public Status getWorkingState() {
        return workingState;
    }

    public void setWorkingState(Status workingState) {
        this.workingState = workingState;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
