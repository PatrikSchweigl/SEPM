package at.qe.sepm.asn_app.models.nursery;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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

    private Date bringStart;
    private Date bringEnd;

    private Date pickUpStart;
    private Date pickUpEnd;

    private int maxOccupancy;
    private Date todaysDate;

    private Date originDate;


    public NurseryInformation(Date bringStart, Date bringEnd, Date pickUpStart, Date pickUpEnd,
                              int maxOccupancy, Date todaysDate, Date originDate) {
        this.bringStart = bringStart;
        this.bringEnd = bringEnd;
        this.pickUpStart = pickUpStart;
        this.pickUpEnd = pickUpEnd;
        this.maxOccupancy = maxOccupancy;
        this.todaysDate = todaysDate;
        this.originDate = originDate;
    }

    public NurseryInformation(){}

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
    
    public String getFormattedOriginDate(){
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
        }
        else if(!(obj instanceof NurseryInformation)) {
            return false;
        }

        NurseryInformation other = (NurseryInformation) obj;
        if (this.bringStart.equals(other.bringStart) &&
                this.pickUpStart.equals(other.pickUpStart) &&
                this.bringEnd.equals(other.bringEnd) &&
                this.pickUpEnd.equals(other.pickUpEnd) &&
                this.maxOccupancy == other.maxOccupancy) {
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public String toString() {
        return  "BringStart: " + bringStart + "\n" +
                "BringEnd: " + bringEnd + "\n" +
                "PickUpStart: " + pickUpStart + "\n" +
                "PickUpEnd: " + pickUpEnd + "\n" +
                "MaxOccupancy: " + maxOccupancy;
    }

    public String getBringDuration(){
        Calendar calb = Calendar.getInstance(TimeZone.getTimeZone("Europe/Vienna"));
        calb.setTime(this.getBringStart());
        int hb = calb.get(Calendar.HOUR_OF_DAY);
        int minb = calb.get(Calendar.MINUTE);
        calb.setTime(this.getBringEnd());
        int he = calb.get(Calendar.HOUR_OF_DAY);
        int mine = calb.get(Calendar.MINUTE);

        return hb +":" +minb + " Uhr - " + he +":" + mine +" Uhr";
    }
    public String getPickUpDuration(){
        Calendar calp = Calendar.getInstance(TimeZone.getTimeZone("Europe/Vienna"));
        calp.setTime(this.getPickUpStart());
        int hb = calp.get(Calendar.HOUR_OF_DAY);
        int minb = calp.get(Calendar.MINUTE);
        calp.setTime(this.getPickUpEnd());
        int he = calp.get(Calendar.HOUR_OF_DAY);
        int mine = calp.get(Calendar.MINUTE);

        return hb +":" +minb + " Uhr - " + he +":" + mine +" Uhr";
    }
    
    public String getBringDurationNew(){
    	String start = new SimpleDateFormat("H:mm").format(this.bringStart);
    	String end = new SimpleDateFormat("H:mm").format(this.bringEnd);


        return start + " - " + end + " Uhr";
    }
    public String getPickUpDurationNew(){
    	String start = new SimpleDateFormat("H:mm").format(this.pickUpStart);
    	String end = new SimpleDateFormat("H:mm").format(this.pickUpEnd);


        return start + " - " + end + " Uhr";
    }
}
