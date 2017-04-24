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
 */
@Entity
public class NurseryInformation implements Persistable<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private Date bringTimeStart;
    @NotNull
    private Date bingTimeEnd;
    @NotNull
    private Date pickUpTimeStart;
    @NotNull
    private Date pickUpTimeEnd;
    @NotNull
    private int maxOccupancy;

    public NurseryInformation(Date bringTimeStart, Date bingTimeEnd, Date pickUpTimeStart, Date pickUpTimeEnd, int maxOccupancy) {
        this.bringTimeStart = bringTimeStart;
        this.bingTimeEnd = bingTimeEnd;
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

    public Date getBingTimeEnd() {
        return bingTimeEnd;
    }

    public void setBingTimeEnd(Date bingTimeEnd) {
        this.bingTimeEnd = bingTimeEnd;
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

}
