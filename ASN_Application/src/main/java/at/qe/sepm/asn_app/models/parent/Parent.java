package at.qe.sepm.asn_app.models.parent;



import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.child.Sibling;
import at.qe.sepm.asn_app.models.general.Person;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by zerus on 17.03.17.
 */
@Entity
public class Parent implements Persistable<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String imgName;

    @OneToMany
    private Set<Child> listChildren;
    @OneToMany
    private Set<Caregiver> listCaregivers;
    @OneToMany
    @ElementCollection
    private Set<Assignment> listAssignments;

    public Parent(String firstName, String lastName, String userName, String password,
                  String imgName, Set<Child> listChildren, Set<Caregiver> listCaregivers,
                  Set<Assignment> listAssignments) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.imgName = imgName;
        this.listChildren = listChildren;
        this.listCaregivers = listCaregivers;
        this.listAssignments = listAssignments;
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

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isNew() {
        return (null == firstName && null == lastName);
    }
}
