package at.qe.sepm.asn_app.models.nursery;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.03.2017
 * <p>
 * NurseryInformation stores the bring- and pick up times for children in the nursery.
 * It also stores an integer which specifies the maximum occupancy of children of the nursery.
 */
@Entity
public class NurseryInformation implements Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private Date bringStart;
    @NotNull
    private Date bringEnd;
    @NotNull
    private Date pickUpStart;
    @NotNull
    private Date pickUpEnd;
    @NotNull
    private int maxOccupancy;
    @Column(columnDefinition = "int default 0")
    private int currentOccupancy;
    @NotNull
    private Date todaysDate;
    @NotNull
    private Date originDate;


    public NurseryInformation() {
    }

    public Date getBringStart() {
        return bringStart;
    }

    public void setBringStart(Date bringStart) {
        this.bringStart = bringStart;
    }

    public Date getBringEnd() {
        return bringEnd;
    }

    public void setBringEnd(Date bringEnd) {
        this.bringEnd = bringEnd;
    }

    public Date getPickUpStart() {
        return pickUpStart;
    }

    public void setPickUpStart(Date pickUpStart) {
        this.pickUpStart = pickUpStart;
    }

    public Date getPickUpEnd() {
        return pickUpEnd;
    }

    public void setPickUpEnd(Date pickUpEnd) {
        this.pickUpEnd = pickUpEnd;
    }

    public Date getTodaysDate() {
        return todaysDate;
    }

    public void setTodaysDate(Date todaysDate) {
        this.todaysDate = todaysDate;
    }

    public Date getCurrentDate() {
        return todaysDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.todaysDate = currentDate;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }


    public Date getOriginDate() {
        return originDate;
    }

    public String getFormattedOriginDate() {
        return new SimpleDateFormat("dd-MM-yyyy").format(originDate);
    }

    public void setOriginDate(Date originDate) {
        this.originDate = originDate;
    }

    @Override
    public Long getId() {
        return id;
    }


    @Override
    public boolean isNew() {
        return (null == pickUpStart && null == pickUpEnd);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof NurseryInformation)) {
            return false;
        }

        NurseryInformation other = (NurseryInformation) obj;
        if (originDate.getYear() == other.originDate.getYear() &&
                originDate.getMonth() == other.originDate.getMonth() &&
                originDate.getDay() == other.originDate.getDay() &&

                todaysDate.getYear() == other.todaysDate.getYear() &&
                todaysDate.getMonth() == other.todaysDate.getMonth() &&
                todaysDate.getDay() == other.todaysDate.getDay() &&

                bringStart.getHours() == other.bringStart.getHours() &&
                bringStart.getMinutes() == other.bringStart.getMinutes() &&

                pickUpStart.getHours() == other.pickUpStart.getHours() &&
                pickUpStart.getMinutes() == other.pickUpStart.getMinutes() &&

                bringEnd.getHours() == other.bringEnd.getHours() &&
                bringEnd.getMinutes() == other.bringEnd.getMinutes() &&

                pickUpEnd.getHours() == other.pickUpEnd.getHours() &&
                pickUpEnd.getMinutes() == other.pickUpEnd.getMinutes() &&

                this.maxOccupancy == other.maxOccupancy) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public String toString() {
        return "OriginDate: " + originDate + "\n" +
                "TodaysDate: " + todaysDate + "\n" +
                "BringStart: " + bringStart + "\n" +
                "BringEnd: " + bringEnd + "\n" +
                "PickUpStart: " + pickUpStart + "\n" +
                "PickUpEnd: " + pickUpEnd + "\n" +
                "MaxOccupancy: " + maxOccupancy;
    }

    public String getBringDurationNew() {
        String start = new SimpleDateFormat("H:mm").format(this.bringStart);
        String end = new SimpleDateFormat("H:mm").format(this.bringEnd);


        return start + " - " + end + " Uhr";
    }

    public String getPickUpDurationNew() {
        String start = new SimpleDateFormat("H:mm").format(this.pickUpStart);
        String end = new SimpleDateFormat("H:mm").format(this.pickUpEnd);


        return start + " - " + end + " Uhr";
    }

    public int getCurrentOccupancy() {
        return currentOccupancy;
    }

    public void setCurrentOccupancy(int currentOccupancy) {
        this.currentOccupancy = currentOccupancy;
    }
}
