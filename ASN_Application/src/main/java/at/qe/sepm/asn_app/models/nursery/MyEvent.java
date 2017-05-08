package at.qe.sepm.asn_app.models.nursery;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MyEvent extends org.primefaces.model.DefaultScheduleEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	public MyEvent(){}
	
	public MyEvent(String stringId, String description, Date beginDate, Date endDate){
		super(description, beginDate, endDate);
		super.setId(stringId);
	}
}
