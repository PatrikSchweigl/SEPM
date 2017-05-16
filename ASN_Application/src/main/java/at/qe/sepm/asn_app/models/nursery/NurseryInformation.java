package at.qe.sepm.asn_app.models.nursery;

import at.qe.sepm.asn_app.models.general.PairTime;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.03.2017
 *
 * NurseryInformation stores the bring- and pick up times for children in the nursery.
 * It also stores an integer which specifies the maximum occupancy of children of the nursery.
 */
@Entity
public class NurseryInformation implements Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    private PairTime bringDuration;

    @ManyToOne(optional = false)
    private PairTime pickUpDuration;

    private int maxOccupancy;
    private Date currentDate;


    public NurseryInformation(PairTime bringDuration, PairTime pickUpDuration, int maxOccupancy, Date currentDate) {
        this.bringDuration = bringDuration;
        this.pickUpDuration = pickUpDuration;
        this.maxOccupancy = maxOccupancy;
        this.currentDate = currentDate;
    }

    public NurseryInformation(){}

    public PairTime getBringDuration() {
        return bringDuration;
    }

    public void setBringDuration(PairTime bringDuration) {
        this.bringDuration = bringDuration;
    }

    public PairTime getPickUpDuration() {
        return pickUpDuration;
    }

    public void setPickUpDuration(PairTime pickUpDuration) {
        this.pickUpDuration = pickUpDuration;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }


    @Override
    public Long getId() {
        return id;
    }


    @Override
    public boolean isNew() {
        return (null == bringDuration && null == pickUpDuration);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        else if(!(obj instanceof NurseryInformation)) {
            return false;
        }

        NurseryInformation other = (NurseryInformation) obj;
        if (this.bringDuration.equals(other.bringDuration) &&
                this.pickUpDuration.equals(other.pickUpDuration) &&
                this.maxOccupancy == other.maxOccupancy) {
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public String toString() {
        return  "BringDuration: " + bringDuration + "\n" +
                "PickUpDuration: " + pickUpDuration + "\n" +
                "MaxOccupancy: " + maxOccupancy;
    }
}
