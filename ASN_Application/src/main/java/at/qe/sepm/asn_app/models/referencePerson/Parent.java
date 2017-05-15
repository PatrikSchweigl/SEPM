package at.qe.sepm.asn_app.models.referencePerson;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.general.FamilyStatus;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 14.05.2017
 */
@Entity
public class Parent extends UserData {

    private static final long serialVersionUID = 1L;

    private String imgName;
    @OneToMany
    @ElementCollection
    private Set<Child> children;
    @OneToMany
    @ElementCollection
    private Set<Assignment> assignments;
    @Enumerated(EnumType.STRING)
    private FamilyStatus familyStatus;
    private boolean status;


    public Parent(){}

    public Parent(String password, String username, String firstName, String lastName,
                  String location, String streetName, String postcode, UserRole userRole,
                   String imgName, Set<Child> children, Set<Assignment> assignments,
                  FamilyStatus familyStatus, boolean status, String birthday) {
        super(password, username, firstName, lastName, location, streetName, postcode, userRole, birthday);
        this.imgName = imgName;
        this.children = children;
        this.assignments = assignments;
        this.familyStatus = familyStatus;
        this.status = status;
    }


    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public Set<Child> getChildren() {
        return children;
    }

    public void setChildren(Set<Child> children) {
        this.children = children;
    }

    public Set<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Set<Assignment> assignments) {
        this.assignments = assignments;
    }

    public FamilyStatus getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(FamilyStatus familyStatus) {
        this.familyStatus = familyStatus;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    /**
     * This method doesn't check for equality of every object because it is not needed.
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Parent)) {
            return false;
        }

        Parent other = (Parent) obj;
        if (this.getFirstName().equals(other.getFirstName()) &&
                this.getLastName().equals(other.getLastName()) &&
                this.getUserRole().equals(other.getUserRole()) &&
                this.familyStatus.equals(other.familyStatus) &&
                this.getBirthday().equals(other.getBirthday())) {
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "Username: " + getUsername() + "\n" +
                "FirstName: " + getFirstName() + "\n" +
                "LastName: " + getLastName() + "\n" +
                "Birthday: " + getBirthday() + "\n" +
                "FamilyStatus: " + familyStatus + "\n" +
                "UserRole: " + getUserRole() + "\n" +
                "Status: " + status + "\n" +
                "Postcode: " + getPostcode() + "\n" +
                "Location: " + getLocation() + "\n" +
                "StreetName: " + getStreetName() + "\n" +
                "Children: " + children + "\n" +
                "Assignments: " + assignments + "\n" +
                "ImgName: " + imgName;
    }
}
