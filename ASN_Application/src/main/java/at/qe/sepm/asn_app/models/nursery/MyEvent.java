package at.qe.sepm.asn_app.models.nursery;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MyEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String stringId;
	private String descript;
	private Date beginDate;
	private Date endDate;
	
	public MyEvent(){}
	
	public MyEvent(String stringId, String description, Date beginDate, Date endDate){
		this.setStringId(stringId);
		this.endDate = endDate;
		this.beginDate = beginDate;
		this.setDescript(description);
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public String getStringId() {
		return stringId;
	}

	public void setStringId(String stringId) {
		this.stringId = stringId;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		else if (!(obj instanceof MyEvent)) {
			return false;
		}

		MyEvent other = (MyEvent) obj;
		if (this.beginDate.equals(other.beginDate) &&
				this.descript.equals(other.descript) &&
				this.endDate.equals(other.endDate) &&
				this.stringId.equals(other.stringId)) {
			return true;
		}
		else {
			return false;
		}
	}


	@Override
	public String toString() {
		return "BeginDate: " + beginDate + "\n" +
				"Descript: " + descript + "\n" +
				"EndDate: " + endDate + "\n"+
				"StringId: " + stringId;
	}
}
