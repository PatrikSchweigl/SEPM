package at.qe.sepm.asn_app.models.child;


import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.models.referencePerson.Caregiver;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
//import org.joda.time.DateTime;
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
    //private DateTime birthday;
    private String birthday;
    private String imgName;
    @ElementCollection(targetClass=String.class)
    private Set<String> furtherRemarks;
    private Parent parent1;
    //private String parent1;
    private Parent parent2;
   // private String parent2;
    private String emergencyNumber;
    @ElementCollection(targetClass=String.class)
    private Set<String> listAllergies;
    @ElementCollection(targetClass=String.class)
    private Set<String> listFoodIntolerances;
    @OneToMany
    private Set<Sibling> listSiblings;
    @OneToMany
    private Set<PairTime> pairTime;
    @Enumerated(EnumType.STRING)
    private Custody custody;
    private Religion religion;
    @OneToMany
    private Set<Caregiver> cargivers;


    /* CONSTRUCTORS */
    public Child() {}

    public Child(String firstName, String lastName, String birthday, Religion religion) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.religion = religion;
    }

    public Child(String firstName, String lastName, String birthday, String imgName, Set<String> furtherRemarks,
                 Parent parent1, Parent parent2, String emergencyNumber, Set<String> listAllergies, Set<String> listFoodIntolerances,
                 Set<Sibling> listSiblings, Set<PairTime> pairTime, Custody custody, Religion religion, Set<Caregiver> cargivers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.imgName = imgName;
        this.furtherRemarks = furtherRemarks;
        this.parent1 = parent1;
        this.parent2 = parent2;
        this.emergencyNumber = emergencyNumber;
        this.listAllergies = listAllergies;
        this.listFoodIntolerances = listFoodIntolerances;
        this.listSiblings = listSiblings;
        this.pairTime = pairTime;
        this.custody = custody;
        this.religion = religion;
        this.cargivers = cargivers;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
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

    public Parent getParent1() {
        return parent1;
    }

    public void setParent1(Parent parent1) {
        this.parent1 = parent1;
    }

    public Parent getParent2() {
        return parent2;
    }

    public void setParent2(Parent parent2) {
        this.parent2 = parent2;
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

    public String getEmergencyNumber() {
        return emergencyNumber;
    }

    public void setEmergencyNumber(String emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
    }

    public Set<PairTime> getPairTime() {
        return pairTime;
    }

    public void setPairTime(Set<PairTime> pairTime) {
        this.pairTime = pairTime;
    }

    public Set<Caregiver> getCargivers() {
        return this.cargivers;
    }

    public void setCargivers(Set<Caregiver> cargivers) {
        this.cargivers = cargivers;
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
