package at.qe.sepm.asn_app.models.referencePerson;



import at.qe.sepm.asn_app.models.User;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by zerus on 17.03.17.
 */
@Entity
public class Parent extends User{
    private static final long serialVersionUID = 1L;

    private String imgName;
    @NotNull
    private String location;
    @NotNull
    private String postcode;
    @NotNull
    private String streetName;
    @OneToMany
    private Set<Child> listChildren;
    @OneToMany
    @ElementCollection
    private Set<Assignment> listAssignments;
    @Enumerated(EnumType.STRING)
    private FamilyStatus familyStatus;
    private boolean status;

    public Parent(String password, String username, String firstName, String lastName,
                  String imgName, String location, String postcode, String streetName, Set<Child> listChildren,
                  Set<Assignment> listAssignments, FamilyStatus familyStatus, boolean status) {
        super(password, username, firstName, lastName, UserRole.PARENT);
        this.imgName = imgName;
        this.location = location;
        this.postcode = postcode;
        this.streetName = streetName;
        this.listChildren = listChildren;
        this.listAssignments = listAssignments;
        this.familyStatus = familyStatus;
        this.status = status;
    }

    public Parent(){}

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
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
