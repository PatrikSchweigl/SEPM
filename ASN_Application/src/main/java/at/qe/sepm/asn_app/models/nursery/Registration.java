package at.qe.sepm.asn_app.models.nursery;

import at.qe.sepm.asn_app.models.child.Child;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 26.05.2017
 */
@Entity
public class Registration implements Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String note;
    @NotNull
    @ManyToOne(optional = false)
    private Child child;
    @NotNull
    private Date date;  //date should hold Year/Month/Day only - no timestamps -> easier to compare
    @NotNull
    private Date bringdate;

    public Registration(){

    }


    public Registration(String note, Child child, Date date, Date bringDate) {
        this.note = note;
        this.child = child;
        this.date = date;
        this.setBringdate(bringDate);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return (this.date == null && this.child == null);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
    
    public String getFormattedBringDate(){
    	return new SimpleDateFormat("hh:mm").format(bringdate);
    }
    
    public String getFormattedBringDateFull(){
    	return new SimpleDateFormat("dd-MM-yyyy hh:mm").format(bringdate);
    }


	public Date getBringdate() {
		return bringdate;
	}


	public void setBringdate(Date bringdate) {
		this.bringdate = bringdate;
	}


	@Override
	public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        else if (obj == null) {
            return false;
        }
        else if (!(obj instanceof Registration)) {
            return false;
        }

        Registration other = (Registration) obj;
        if (id.equals(other.id)) {
            return true;
        }

        return (note.equals(other.note) &&
                child.equals(other.child) &&
                date.getYear() == other.date.getYear() &&
                date.getMonth() == other.date.getMonth() &&
                date.getDay() == other.date.getDay() &&
                bringdate.getYear() == other.bringdate.getYear() &&
                bringdate.getMonth() == other.bringdate.getMonth() &&
                bringdate.getDay() == other.bringdate.getDay());
    }
}
