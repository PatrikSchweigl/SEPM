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


    public Employee(){//required for jpa repository
    }

    public Employee(String password, String username, String firstName, String lastName, String location,
                    String streetName, String postcode, UserRole userRole, Religion religion, String phoneNumber,
                    FamilyStatus familyStatus, Status workingState, WorkRole workRole, String birthday, boolean isAdmin) {
        super(password, username, firstName, lastName, location, streetName, postcode, userRole, birthday);
        this.religion = religion;
        this.phoneNumber = phoneNumber;
        this.familyStatus = familyStatus;
        this.workingState = workingState;
        this.workRole = workRole;
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


    /**
     * This method doesn't check for every attribute because some are unnecessary to check (e.g. username).
     * @param obj the object to compare
     * @return true iff the current instance of Employee and the parameter object are the same Employee.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        else if (!(obj instanceof Employee)) {
            return false;
        }

        Employee other = (Employee) obj;
        if (this.getFamilyStatus().equals(other.getFamilyStatus()) &&
                this.getPhoneNumber().equals(other.getPhoneNumber()) &&
                this.getReligion().equals(other.getReligion()) &&
                this.getBirthday().equals(other.getBirthday()) &&
                this.getFirstName().equals(other.getFirstName()) &&
                this.getLastName().equals(other.getLastName()) &&
                this.getLocation().equals(other.getLocation()) &&
                this.getPostcode().equals(other.getPostcode()) &&
                this.getStreetName().equals(other.getStreetName())) {
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public String toString() {
        return "Id: " + String.valueOf(getId()) + "\n" +
                "Username: " + getUsername() + "\n" +
                "First name: " + getFirstName() + "\n" +
                "Last name: " + getLastName() + "\n" +
                "Birthday: " + getBirthday() + "\n" +
                "Religion: " + religion + "\n" +
                "Phone number: " + getPhoneNumber() + "\n" +
                "Location: " + getLocation() + "\n" +
                "Postcode: " + getPostcode() + "\n" +
                "Street name: " + getStreetName() + "\n" +
                "Family status: " + getFamilyStatus() + "\n" +
                "Working state: " + getWorkingState() + "\n" +
                "Work role: " + getWorkRole() + "\n" +
                "User role: " + getUserRole();
    }
}
