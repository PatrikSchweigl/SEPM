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
}
