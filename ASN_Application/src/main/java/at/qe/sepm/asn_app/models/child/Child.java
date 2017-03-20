package at.qe.sepm.asn_app.models.child;


import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by zerus on 17.03.17.
 */
/** Edit by Stefan Mattersberger on 18.03.2017 */
@Entity
public class Child implements Persistable<Long>{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String imgName;   // tmp, I don't know what data type profilePicture should be.
    @ElementCollection(targetClass=String.class)
    private Set<String> furtherRemarks;
    private Parent father;
    private Parent mother;
    //private EmergencyContact emergencyContact;
    @ElementCollection(targetClass=String.class)
    private Set<String> listAllergies;
    @ElementCollection(targetClass=String.class)
    private Set<String> listFoodIntolerances;
    @OneToMany
    private Set<Sibling> listSiblings;
    private Custody custody;
    private Religion religion;


    public Child(String firstName, String lastName, Date birthday, String imgName,
                 Set<String> furtherRemarks, Parent father, Parent mother, Set<String> listAllergies,
                 Set<String> listFoodIntolerances, Set<Sibling> listSiblings, Custody custody, Religion religion) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.imgName = imgName;
        this.furtherRemarks = furtherRemarks;
        this.father = father;
        this.mother = mother;
        this.listAllergies = listAllergies;
        this.listFoodIntolerances = listFoodIntolerances;
        this.listSiblings = listSiblings;
        this.custody = custody;
        this.religion = religion;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public Set<String> getFurtherRemarks() {
        return furtherRemarks;
    }

    public void setFurtherRemarks(Set<String> furtherRemarks) {
        this.furtherRemarks = furtherRemarks;
    }

    public Parent getFather() {
        return father;
    }

    public void setFather(Parent father) {
        this.father = father;
    }

    public Parent getMother() {
        return mother;
    }

    public void setMother(Parent mother) {
        this.mother = mother;
    }

    public Set<String> getListAllergies() {
        return listAllergies;
    }

    public void setListAllergies(Set<String> listAllergies) {
        this.listAllergies = listAllergies;
    }

    public Set<String> getListFoodIntolerances() {
        return listFoodIntolerances;
    }

    public void setListFoodIntolerances(Set<String> listFoodIntolerances) {
        this.listFoodIntolerances = listFoodIntolerances;
    }

    public Set<Sibling> getListSiblings() {
        return listSiblings;
    }

    public void setListSiblings(Set<Sibling> listSiblings) {
        this.listSiblings = listSiblings;
    }


    public Custody getCustody() {
        return custody;
    }

    public void setCustody(Custody custody) {
        this.custody = custody;
    }

    public Religion getReligion() {
        return religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
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
