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
    private MyEvent event;
    private String description;
    @ManyToOne(optional = false)
    private UserData  sender;
    @ManyToOne(optional = false)
    private UserData receiver;


    public Task(){}

    public Task(String description, MyEvent event, UserData sender, UserData receiver) {
        this.description = description;
        this.event = event;
        this.sender = sender;
        this.receiver = receiver;
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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return getSender() == null;
    }


	public MyEvent getEvent() {
		return event;
	}

	public void setEvent(MyEvent event) {
		this.event = event;
	}

}
