package at.qe.sepm.asn_app.models.nursery;

import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.03.2017
 *
 * NurseryInformation stores the bring- and pick up times for children in the nursery.
 * It also stores an integer which specifies the maximum occupancy of children of the nursery.
 */
// TODO Change bring- and pick up time to PairTime
@Entity
public class NurseryInformation implements Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private Date bringTimeStart;
    @NotNull
    private Date bringTimeEnd;
    @NotNull
    private Date pickUpTimeStart;
    @NotNull
    private Date pickUpTimeEnd;
    @NotNull
    private int maxOccupancy;


    public NurseryInformation(Date bringTimeStart, Date bringTimeEnd, Date pickUpTimeStart, Date pickUpTimeEnd, int maxOccupancy) {
        this.bringTimeStart = bringTimeStart;
        this.bringTimeEnd = bringTimeEnd;
        this.pickUpTimeStart = pickUpTimeStart;
        this.pickUpTimeEnd = pickUpTimeEnd;
        this.maxOccupancy = maxOccupancy;
    }


    public Date getBringTimeStart() {
        return bringTimeStart;
    }

    public void setBringTimeStart(Date bringTimeStart) {
        this.bringTimeStart = bringTimeStart;
    }

    public Date getbringTimeEnd() {
        return bringTimeEnd;
    }

    public void setbringTimeEnd(Date bringTimeEnd) {
        this.bringTimeEnd = bringTimeEnd;
    }

    public Date getPickUpTimeStart() {
        return pickUpTimeStart;
    }

    public void setPickUpTimeStart(Date pickUpTimeStart) {
        this.pickUpTimeStart = pickUpTimeStart;
    }

    public Date getPickUpTimeEnd() {
        return pickUpTimeEnd;
    }

    public void setPickUpTimeEnd(Date pickUpTimeEnd) {
        this.pickUpTimeEnd = pickUpTimeEnd;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }


    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public boolean isNew() {
        return (null == bringTimeStart && null == pickUpTimeEnd);
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
        if (this.bringTimeStart.equals(other.bringTimeStart) &&
                this.bringTimeEnd.equals(other.bringTimeEnd) &&
                this.maxOccupancy == other.maxOccupancy &&
                this.pickUpTimeStart.equals(other.pickUpTimeStart) &&
                this.pickUpTimeEnd.equals(other.pickUpTimeEnd)) {
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public String toString() {
        return "BringTimeStart: " + bringTimeStart + "\n" +
                "BringTimeEnd: " + bringTimeEnd + "\n" +
                "PickUpTimeStart: " + pickUpTimeStart + "\n" +
                "PickUpTimeEnd: " + pickUpTimeEnd + "\n" +
                "MaxOccupancy: " + maxOccupancy;
    }
}
