package at.qe.sepm.asn_app.models.referencePerson;



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
    private String imgName;
    @NotNull
    private String location;
    @NotNull
    private String postcode;
    private String streetName;
    @OneToMany
    private Set<Child> listChildren;
    @OneToMany
    private Set<Caregiver> listCaregivers;
    @OneToMany
    @ElementCollection
    private Set<Assignment> listAssignments;
    private FamilyStatus familyStatus;
    private boolean isRegistered;

    public Parent(String firstName, String lastName, String userName, String password, String imgName,
                  String location, String postcode, String streetName, Set<Child> listChildren,
                  Set<Caregiver> listCaregivers, Set<Assignment> listAssignments, FamilyStatus familyStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.imgName = imgName;
        this.location = location;
        this.postcode = postcode;
        this.streetName = streetName;
        this.listChildren = listChildren;
        this.listCaregivers = listCaregivers;
        this.listAssignments = listAssignments;
        this.familyStatus = familyStatus;
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

    public Set<Caregiver> getListCaregivers() {
        return listCaregivers;
    }

    public void setListCaregivers(Set<Caregiver> listCaregivers) {
        this.listCaregivers = listCaregivers;
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

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isNew() {
        return (null == firstName && null == lastName);
    }
}
