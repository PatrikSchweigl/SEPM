package at.qe.sepm.asn_app.models.referencePerson;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.models.nursery.Task;
import com.fasterxml.jackson.annotation.JsonIgnore;
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


    @JsonIgnore
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
        return children.size() > 0;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }




    /**
     * This method doesn't check for equality of every object because it is not needed.
     * @param other The object to be compared. If obj is not an instance of Parent then false is returned immediately.
     * @return <code>true</code> iff the current instance of Parent and the parameter are the same; <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof Parent) && (getId() != null)
                ? getId().equals(((Parent) other).getId())
                : (other == this);
    }
    /*public boolean equals(Object obj) {
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
    }*/

    @Override
    public int hashCode() {
        return (getId() != null)
                ? (getClass().hashCode() + getId().hashCode())
                : super.hashCode();
    }

    public String getChildrenNames(){
        String s = "";
        for(Child c: children){
            s += c.getFirstName() + " " + c.getLastName() + ", ";
        }
        return s;
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
                "Children: " + getChildrenNames() + "\n" +
                "Tasks: " + tasks + "\n";
    }
}
