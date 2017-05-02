package at.qe.sepm.asn_app.models.referencePerson;



import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.general.FamilyStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by zerus on 17.03.17.
 */
@Entity
public class Parent extends UserData {
    private static final long serialVersionUID = 1L;

    private String imgName;
    @OneToMany
    @ElementCollection
    private Set<Child> listChildren;
    @OneToMany
    @ElementCollection
    private Set<Assignment> listAssignments;
    @Enumerated(EnumType.STRING)
    private FamilyStatus familyStatus;
    private boolean status;



    public Parent(){}


    public Parent(String password, String username, String firstName, String lastName,
                  String location, String streetName, String postcode, UserRole userRole,
                  String imgName, String location1, String postcode1, String streetName1,
                  Set<Child> listChildren, Set<Assignment> listAssignments, FamilyStatus familyStatus, boolean status) {
        super(password, username, firstName, lastName, location, streetName, postcode, userRole);
        this.imgName = imgName;
        this.listChildren = listChildren;
        this.listAssignments = listAssignments;
        this.familyStatus = familyStatus;
        this.status = status;
    }



    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public Set<Child> getListChildren() {
        return listChildren;
    }

    public void setListChildren(Set<Child> listChildren) {
        this.listChildren = listChildren;
    }

    public Set<Assignment> getListAssignments() {
        return listAssignments;
    }

    public void setListAssignments(Set<Assignment> listAssignments) {
        this.listAssignments = listAssignments;
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
                this.familyStatus.equals(other.familyStatus)) {
            return true;
        }
        return false;
    }
}
