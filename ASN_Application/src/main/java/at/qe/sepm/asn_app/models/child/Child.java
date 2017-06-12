package at.qe.sepm.asn_app.models.child;


import at.qe.sepm.asn_app.models.Gender;
import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.models.referencePerson.Caregiver;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import org.springframework.data.domain.Persistable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;


/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 17.03.17.
 * Edit by Stefan Mattersberger on 18.03.2017
 *
 * Child contains all basic information for a child such as the name, birthday, gender,
 * the parents, an emergency number, the religion, allergies and food intolerances.
 * Additionally Child holds information about the caregivers in case a Parent is not available,
 * the custody regarding that the parents are divorced and references to all siblings of the child.
 * In contrary to Employee and Parent Child does not inherit from UserData.
 * @see at.qe.sepm.asn_app.models.employee.Employee
 * @see Caregiver
 * @see Custody
 * @see Gender
 * @see Parent
 * @see Sibling
 * @see at.qe.sepm.asn_app.models.UserData
 */
@Entity
@Transactional
public class Child implements Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String birthday;
    private String imgName;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    @ManyToOne(optional = false)
    private Parent parent1;

    @ManyToOne // only 1 parent required for child
    private Parent parent2;

    @NotNull
    private String emergencyNumber;
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private Set<String> allergies;
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private Set<String> foodIntolerances;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "child", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Sibling> siblings;

    @Enumerated(EnumType.STRING)
    private Custody custody;

    @Enumerated(EnumType.STRING)
    private Religion religion;

    //@ElementCollection(targetClass = Caregiver.class)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "child", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Caregiver> caregivers;


    /* CONSTRUCTORS */
    public Child() {}

    public Child(String firstName, String lastName, String birthday, String imgName, Gender gender, Parent parent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.imgName = imgName;
        this.gender = gender;
        this.parent1 = parent;
    }

    public Child(String firstName, String lastName, String birthday, String imgName, Gender gender, Parent par1, Parent par2, String emergencyNumber, Custody custody, Religion religion) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.imgName = imgName;
        this.gender = gender;
        this.parent1 = par1;
        this.parent2 = par2;
        this.emergencyNumber = emergencyNumber;
        this.custody = custody;
        this.religion = religion;
    }

    /**
     * Full constructor
     */
    public Child(String firstName, String lastName, String birthday, String imgName, Gender gender, Parent par1,
                 Parent par2, String emergencyNumber, Set<String> allergies, Set<String> foodIntolerances,
                 Set<Sibling> siblings, Custody custody, Religion religion, Set<Caregiver> caregivers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.imgName = imgName;
        this.gender = gender;
        this.parent1 = par1;
        this.parent2 = par2;
        this.emergencyNumber = emergencyNumber;
        this.allergies = allergies;
        this.foodIntolerances = foodIntolerances;
        this.siblings = siblings;
        this.custody = custody;
        this.religion = religion;
        this.caregivers = caregivers;
    }


    public String getPrimaryParentFullName(){
        return parent1.getFirstName() + " " + parent1.getLastName();
    }

    public Parent getPrimaryParent() {
        return parent1;
    }

    public void setPrimaryParent(Parent p){
        parent1= p;
    }

    public Parent getParent2() {
        return parent2;
    }

    public void setParent2(Parent parent2) {
        this.parent2 = parent2;
    }

    public void addCaregiver(Caregiver c){
        caregivers.add(c);
    }

    public void addAllergy(String s){
        allergies.add(s);
    }

    public void addFoodIntolerance(String s){
        foodIntolerances.add(s);
    }

    public void addSibling(Sibling s){
        siblings.add(s);
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmergencyNumber() {
        return emergencyNumber;
    }

    public void setEmergencyNumber(String emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
    }

    public Set<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(Set<String> allergies) {
        this.allergies = allergies;
    }

    public Set<String> getFoodIntolerances() {
        return foodIntolerances;
    }

    public void setFoodIntolerances(Set<String> foodIntolerances) {
        this.foodIntolerances = foodIntolerances;
    }

    public Set<Sibling> getSiblings() {
        return siblings;
    }

    public void setSiblings(Set<Sibling> siblings) {
        this.siblings = siblings;
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

    public Set<Caregiver> getCaregivers() {
        return caregivers;
    }

    public void setCaregivers(Set<Caregiver> caregivers) {
        this.caregivers = caregivers;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return (null == firstName && null == lastName);
    }


    /**
     * Equals for Child doesn't check for equality of every attribute because
     * even though some attributes like religion may be different the child
     * could still be the "same" person. We focus only on the main attributes
     * to determine equality.
     * @param obj The object to be compared. If it's not an instance of Child then <code>false</code> gets returned immediately.
     * @return <code>true</code> iff the main attributes are equal of the current child and the parameter; <code>false</code> in any other case.
     * @see Gender
     * @see Parent
     * @see Sibling
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        else if (!(obj instanceof Child)) {
            return false;
        }

        Child other = (Child) obj;
        if (this.birthday.equals(other.birthday) &&
                this.firstName.equals(other.firstName) &&
                this.gender.equals(other.gender) &&
                this.lastName.equals(other.lastName) &&
                this.parent1.equals(other.parent1) &&
                this.parent2.equals(other.parent2)) {
                //this.siblings.equals(other.siblings)) {
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public String toString() {
        return "FirstName: " + firstName + "\n" +
                "LastName: " + lastName + "\n" +
                "Birthday: " + birthday + "\n" +
                "Gender: " + gender + "\n" +
                "Religion: " + religion + "\n" +
                "Parent1: " + parent1.getFirstName() + " " + parent1.getLastName() + "\n" +
                "Parent2: " + parent2.getFirstName() + " " + parent2.getLastName() + "\n" +
                "Custody: " + custody + "\n" +
                "Siblings: " + siblings + "\n" +
                "Allergies: " + allergies + "\n" +
                "FoodIntolerances: " + foodIntolerances + "\n" +
                "EmergencyNumber: " + emergencyNumber + "\n" +
                "Caregivers: " + caregivers;
    }
}
