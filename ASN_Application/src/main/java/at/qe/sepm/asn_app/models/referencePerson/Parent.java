package at.qe.sepm.asn_app.models.referencePerson;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.models.nursery.Task;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 14.05.2017
 *
 * The basic informations about a parent are stored in the superclass UserData.
 * Parent itself has some additional attributes like the children of the parent
 * a profile image, the family status and if the parent is an active user or not.
 * A unique attribute is tasks which holds a set of all tasks that have been assigned to a parent.
 * @see Child
 * @see FamilyStatus
 * @see Task
 * @see UserData
 */
@Entity
public class Parent extends UserData {

    private static final long serialVersionUID = 1L;

    private String imgName;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent1", cascade = CascadeType.ALL, orphanRemoval = true)
    @ElementCollection
    private Set<Child> children;
    @OneToMany(fetch = FetchType.EAGER)
    @ElementCollection
    private Set<Task> tasks;
    @Enumerated(EnumType.STRING)
    private FamilyStatus familyStatus;
    private boolean status;



    public Parent(){}

    public Parent(String username, String password, String firstName, String lastName,
                  String location, String streetName, String postcode, String birthday,
                  String email, String imgName, UserRole userRole, Religion religion,
                  String phoneNumber, boolean notification, Set<Child> children,
                  Set<Task> tasks, FamilyStatus familyStatus, boolean status) {

        super(username, password, firstName, lastName, location, streetName, postcode,
                birthday, email, imgName, userRole, religion, phoneNumber, notification);

        this.imgName = imgName;
        this.children = children;
        this.tasks = tasks;
        this.familyStatus = familyStatus;
        this.status = status;
    }

    public Parent(String s, String parentUserName1, String parentFirstName1, String parentLastName1, String parentLocation1, String parentStreetName1, String parentPostcode1, UserRole parent, String parentImgName1, Set<Child> parentListChildren1, Set<Task> parentListTasks1, FamilyStatus married, boolean b, String s1) {
    }

    public Parent(String s, String passwd, String parentFirstName1, String parentLastName1, String parentLocation1, String parentStreetName1, String parentPostcode1, String s1, String s2, UserRole parent, String parentImageName1, Set<Child> parentListChildren1, Set<Task> parentListTasks1, FamilyStatus verheiratet, boolean b) {
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

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
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
     * @param obj The object to be compared. If obj is not an instance of Parent then false is returned immediately.
     * @return <code>true</code> iff the current instance of Parent and the parameter are the same; <code>false</code> otherwise.
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
                familyStatus.equals(other.familyStatus) &&
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
                //"Children: " + children + "\n" +
                "Tasks: " + tasks + "\n" +
                "ImgName: " + imgName;
    }
}
