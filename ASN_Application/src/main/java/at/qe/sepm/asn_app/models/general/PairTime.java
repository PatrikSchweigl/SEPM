package at.qe.sepm.asn_app.models.general;


import at.qe.sepm.asn_app.models.child.Child;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 17.03.17.
 *
 * PairTime holds two attributes, startTime and endTime, which specify a time range.
 * In this project this time range is used to specify the bring- and pick up- times for children at the nursery.
 *
 * @see Child
 * @see at.qe.sepm.asn_app.repositories.PairTimeRepository
 */
@Entity
public class PairTime implements Persistable<Long>{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date startTime;
    private Date endTime;


    public PairTime(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    @Override
    public Long getId() {
       return id;
    }


    @Override
    public boolean isNew() {
        return (null == endTime && null == startTime);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        else if (!(obj instanceof PairTime)) {
            return false;
        }

        PairTime other = (PairTime) obj;
        if (startTime.equals(other.startTime) &&
                endTime.equals(other.endTime)) {
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "StartTime: " + startTime + "\n" +
                "EndTime: " + endTime + "\n";
    }
}
