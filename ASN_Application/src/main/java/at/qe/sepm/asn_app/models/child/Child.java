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
 * Created by zerus on 17.03.17.
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
    /*@ElementCollection(targetClass=String.class)
    private Set<String> furtherRemarks;
    @ManyToOne(optional = false)
    private Parent parent1;
    @ManyToOne(optional = false)
    private Parent parent2;
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
    @Enumerated(EnumType.STRING)
    private Religion religion;
    @OneToMany
    private Set<Caregiver> caregivers;
    */

    /* CONSTRUCTORS */
    public Child() {}

    public Child(String firstName, String lastName, String birthday, String imgName, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.imgName = imgName;
        this.gender = gender;
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


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return (null == firstName && null == lastName);
    }
}
