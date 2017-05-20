package at.qe.sepm.asn_app.models.child;

import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 17.03.17.
 */
@Entity
public class Absence implements Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String reason;
    private Child child;
    private Date date;


    public Absence(String reason, Child child, Date date) {
        this.reason = reason;
        this.child = child;
        this.date = date;
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public boolean isNew() {
        return (null == child);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        else if (!(obj instanceof Absence)) {
            return false;
        }

        Absence other = (Absence) obj;
        if (child.equals(other.child) &&
                date.equals(other.date) &&
                reason.equals(other.reason)) {
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "Date: " + date + "\n" +
                "Child: " + child + "\n" +
                "Reason: " + reason + "\n";
    }
}
