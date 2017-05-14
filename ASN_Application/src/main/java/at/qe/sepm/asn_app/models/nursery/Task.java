package at.qe.sepm.asn_app.models.nursery;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.employee.Employee;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 06.05.2017
 */
@Entity
public class Task implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String stringId;
	private Date beginDate;
	private Date endDate;
    private String description;
    @ManyToOne(optional = false)
    private UserData  sender;
    @ManyToOne(optional = false)
    private UserData receiver;
    private boolean important;
    private String styleClass;


    public Task(){}

    public Task(String description, String stringId, UserData sender, UserData receiver, Date beginDate, Date endDate) {
        this.description = description;
        this.stringId = stringId;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.sender = sender;
        this.receiver = receiver;
        this.important = false;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserData getSender() {
        return sender;
    }

    public void setSender(UserData sender) {
        this.sender = sender;
    }

    public UserData getReceiver() {
        return receiver;
    }

    public void setReceiver(UserData receiver) {
        this.receiver = receiver;
    }

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStringId() {
		return stringId;
	}

	public void setStringId(String stringId) {
		this.stringId = stringId;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public boolean getImportant() {
		return important;
	}

	public void setImportant(boolean important) {
		this.important = important;
	}


    @Override
    public Long getId() {
        return id;
    }


    @Override
    public boolean isNew() {
        return getSender() == null;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        else if (!(obj instanceof Task)) {
            return false;
        }

        Task other = (Task) obj;
        if (this.beginDate.equals(other.beginDate) &&
                this.description.equals(other.description) &&
                this.endDate.equals(other.endDate) &&
                this.important == other.important &&
                this.receiver.equals(other.receiver) &&
                this.sender.equals(other.sender) &&
                this.stringId.equals(other.stringId) &&
                this.styleClass.equals(other.styleClass)) {
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public String toString() {
        return "Description: " + description + "\n" +
                "BeginDate: " + beginDate + "\n" +
                "EndDate: " + endDate + "\n" +
                "Important: " + important + "\n" +
                "Receiver: " + receiver + "\n" +
                "Sender: " + sender + "\n" +
                "StringId: " + stringId + "\n" +
                "StyleClass: " + styleClass;
    }
}
