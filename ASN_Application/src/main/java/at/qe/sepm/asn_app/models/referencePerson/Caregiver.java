package at.qe.sepm.asn_app.models.referencePerson;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by zerus on 17.03.17.
 */
@Entity
public class Caregiver implements Persistable<Long>{
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Relationship relationship;
    private String imgName;
    private String phoneNumber;

    public Caregiver(){}


    public Caregiver(String firstName, String lastName, Relationship relationship, String imgName, String phoneNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.relationship = relationship;
        this.imgName = imgName;
        this.phoneNumber = phoneNumber;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isNew() {
        return (null == firstName && null == lastName);
    }
}
