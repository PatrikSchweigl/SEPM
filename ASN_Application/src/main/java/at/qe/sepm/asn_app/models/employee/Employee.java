package at.qe.sepm.asn_app.models.employee;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.general.Religion;

import javax.persistence.*;

/**
 * Created by zerus on 17.03.17.
 */
@Entity
public class Employee extends UserData {

    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    private Religion religion;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private FamilyStatus familyStatus;
    @Enumerated(EnumType.STRING)
    private Status workingState;
    @Enumerated(EnumType.STRING)
    private WorkRole workRole;
    private String birthday;

    public Employee(){//required for jpa repository
    }


    public Employee(String password, String username, String firstName, String lastName, String location,
                    String streetName, String postcode, UserRole userRole, Religion religion, String phoneNumber,
                    FamilyStatus familyStatus, Status workingState, WorkRole workRole, String birthday, boolean isAdmin) {
        super(password, username, firstName, lastName, location, streetName, postcode, userRole);
        this.religion = religion;
        this.phoneNumber = phoneNumber;
        this.familyStatus = familyStatus;
        this.workingState = workingState;
        this.workRole = workRole;
        this.birthday = birthday;
    }


    public void setAdmin(boolean admin) {
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public WorkRole getWorkRole() {
        return workRole;
    }

    public void setWorkRole(WorkRole workRole) {
        this.workRole = workRole;
    }
}
