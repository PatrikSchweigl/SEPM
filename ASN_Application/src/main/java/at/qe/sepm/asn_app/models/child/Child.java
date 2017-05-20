package at.qe.sepm.asn_app.models.child;


import at.qe.sepm.asn_app.models.Gender;
import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.models.referencePerson.Caregiver;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
//import org.joda.time.DateTime;
import java.util.Set;


/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 17.03.17.
 */
/** Edit by Stefan Mattersberger on 18.03.2017 */
@Entity
public class Child implements Persistable<Long>{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;



    private String lastName;
    private String birthday;
    private String imgName;
    private Gender gender;


    @ManyToOne(optional = false)
    private Parent parent1;

    @ManyToOne // only 1 parent required for child
    private Parent parent2;

    private String emergencyNumber;
    @ElementCollection(targetClass = String.class)
    private Set<String> allergies;
    @ElementCollection(targetClass = String.class)
    private Set<String> foodIntolerances;


    @OneToMany
    private Set<Sibling> siblings;

    @Enumerated(EnumType.STRING)
    private Custody custody;

    @Enumerated(EnumType.STRING)
    private Religion religion;

    //@ElementCollection(targetClass = Caregiver.class)
    @OneToMany
    private Set<Caregiver> caregivers;

    /*@ElementCollection(targetClass=String.class)
    private Set<String> furtherRemarks;
    @ManyToOne(optional = false)
    private Parent parent1;
    @ManyToOne(optional = false)
    private Parent parent2;
    private String emergencyNumber;
    @ElementCollection(targetClass=String.class)
    private Set<String> allergies;
    @ElementCollection(targetClass=String.class)
    private Set<String> foodIntolerances;
    @OneToMany
    private Set<Sibling> listSiblings;
    @OneToMany
    private Set<PairTime> pairTime;
    @Enumerated(EnumType.STRING)
    private Custody custody;
    @Enumerated(EnumType.STRING)
    private Religion religion;
    @OneToMany
    private Set<Caregiver> caregivers;
    */

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
}
