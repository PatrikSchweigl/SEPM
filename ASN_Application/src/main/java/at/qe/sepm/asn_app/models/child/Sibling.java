package at.qe.sepm.asn_app.models.child;

import org.springframework.data.domain.Persistable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 17.03.17.
 * <p>
 * Sibling is a minimalistic version of Child. In contrast to Child it only holds the full name and birthday.
 *
 * @see Child
 */
@Entity
@Transactional
public class Sibling implements Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String birthday;
    @ManyToOne(optional = false)
    private Child child;

    public Sibling() {
    }

    public Sibling(String firstName, String lastName, String birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
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
        if (!(obj instanceof Sibling)) {
            return false;
        }

        Sibling other = (Sibling) obj;
        return birthday.equals(other.birthday) &&
                firstName.equals(other.firstName) &&
                lastName.equals(other.lastName);
    }


    @Override
    public String toString() {
        return "Name: " + firstName + "\n" +
                "LastName: " + lastName + "\n" +
                "Birthday: " + birthday + "\n";
    }
}
