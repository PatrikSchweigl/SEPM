package at.qe.sepm.asn_app.models.referencePerson;

import org.springframework.data.domain.Persistable;
import org.springframework.transaction.annotation.Transactional;

import at.qe.sepm.asn_app.models.child.Child;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 17.03.17.
 *
 * A caregiver takes responsibility for a child if their parent is not available.
 * This class only has some attributes to reference a caregiver like the full name,
 * a profile picture, a phone number and the relationship status to the child.
 * In contrast to Parent and Employee Caregiver does not inherit from UserData.
 * @see Relationship
 */
@Entity
@Transactional
public class Caregiver implements Persistable<Long>{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Relationship relationship;
    private String imgName;
    private String phoneNumber;
    private boolean eligible;
    private String childname;



    public Caregiver(){}

    public Caregiver(String firstName, String lastName, Relationship relationship, String imgName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.relationship = relationship;
        this.imgName = imgName;
        this.phoneNumber = phoneNumber;
        eligible = false;

    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getFullName(){ return getLastName() + " " + getFirstName();}

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }


    @Override
    public Long getId() {
        return id;
    }


    @Override
    public boolean isNew() {
        return (null == firstName && null == lastName);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        else if (!(obj instanceof Caregiver)) {
            return false;
        }

        Caregiver other = (Caregiver) obj;
        if (this.firstName.equals(other.firstName) &&
                this.lastName.equals(other.lastName) &&
                this.relationship.equals(other.relationship) &&
                this.getPhoneNumber().equals(other.phoneNumber)) {
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public String toString() {
        return "First name: " + firstName + "\n" +
                "Last name: " + lastName + "\n" +
                "Relationship: " + relationship + "\n" +
                "Phone number: " + phoneNumber + "\n";
    }

	public boolean getEligible() {
		return eligible;
	}

	public void setEligible(boolean eligible) {
		this.eligible = eligible;
	}

	public String getChildname() {
		return childname;
	}

	public void setChildname(String childname) {
		this.childname = childname;
	}
}
