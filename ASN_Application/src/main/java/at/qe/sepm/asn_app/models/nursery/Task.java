package at.qe.sepm.asn_app.models.nursery;

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
    private String description;
    @ManyToOne(optional = false)
    private Employee  sender;
    @ManyToOne(optional = false)
    private Parent receiver;
    private Date beginDate;
    private Date endDate;

    public Task(){}

    public Task(String description, Employee sender, Parent receiver, Date beginDate, Date endDate) {
        this.description = description;
        this.sender = sender;
        this.receiver = receiver;
        this.beginDate = beginDate;
        this.setEndDate(endDate);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getSender() {
        return sender;
    }

    public void setSender(Employee sender) {
        this.sender = sender;
    }

    public Parent getReceiver() {
        return receiver;
    }

    public void setReceiver(Parent receiver) {
        this.receiver = receiver;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date date) {
        this.beginDate = date;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return getSender() == null;
    }

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
