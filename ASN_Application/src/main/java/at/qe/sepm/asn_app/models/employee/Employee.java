package at.qe.sepm.asn_app.models.employee;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.general.Religion;

import javax.persistence.*;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 17.03.17.
 *
 * Employee inherits from UserData in which all basic information about a
 * person is stored. The class Employee itself gives further information
 * like religion, the phone number, the family status, if the employee is
 * a pedagogue or a trainee and if the employee is currently active or not.
 * @see FamilyStatus
 * @see Religion
 * @see Status
 * @see UserData
 * @see WorkRole
 */
@Entity
public class Employee extends UserData {

    private static final long serialVersionUID = 1L;


    @Enumerated(EnumType.STRING)
    private FamilyStatus familyStatus;
    @Enumerated(EnumType.STRING)
    private Status workingState;
    @Enumerated(EnumType.STRING)
    private WorkRole workRole;


    public Employee(){//required for jpa repository
    }

    public Employee(String username, String password, String firstName, String lastName,
                    String location, String streetName, String postcode, String birthday,
                    String email, UserRole userRole, FamilyStatus familyStatus, Status workingState,
                    WorkRole workRole) {
        super(username, password, firstName, lastName, location, streetName, postcode, birthday, email, userRole);
        this.familyStatus = familyStatus;
        this.workingState = workingState;
        this.workRole = workRole;
    }

    /**
     * Full constructor
     */
    public Employee(String username, String password, String firstName, String lastName,
                    String location, String streetName, String postcode, String birthday,
                    String email, String imgName, UserRole userRole, Religion religion,
                    String phoneNumber, FamilyStatus familyStatus, Status workingState, WorkRole workRole) {
        super(username, password, firstName, lastName, location, streetName, postcode, birthday, email,
                imgName, userRole, religion, phoneNumber);
        this.familyStatus = familyStatus;
        this.workingState = workingState;
        this.workRole = workRole;
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
                "Location: " + getLocation() + "\n" +
                "Postcode: " + getPostcode() + "\n" +
                "Street name: " + getStreetName() + "\n" +
                "Family status: " + getFamilyStatus() + "\n" +
                "Working state: " + getWorkingState() + "\n" +
                "Religion: " + getReligion() + "\n" +
                "User role: " + getUserRole();
    }
}
