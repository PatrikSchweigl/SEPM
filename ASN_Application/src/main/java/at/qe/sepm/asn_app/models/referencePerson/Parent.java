package at.qe.sepm.asn_app.models.referencePerson;



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
public class Parent implements Persistable<Long>{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String userName;
    @NotNull
    private String password;
    @NotNull
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
    @NotNull
    private FamilyStatus familyStatus;
    private boolean status;
    private UserRole role;

    public Parent(String firstName, String lastName, String userName, String password, String imgName,
                  String location, String postcode, String streetName, Set<Child> listChildren,
                  Set<Assignment> listAssignments, FamilyStatus familyStatus, boolean status, UserRole role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.imgName = imgName;
        this.location = location;
        this.postcode = postcode;
        this.streetName = streetName;
        this.listChildren = listChildren;
        this.listAssignments = listAssignments;
        this.familyStatus = familyStatus;
        this.status = status;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isNew() {
        return (null == firstName && null == lastName);
    }
}
