package at.qe.sepm.asn_app.models.nursery;

import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 17.03.17.
 *
 * A Message contains the name of the sender, the message itself and a date on which it got sent.
 * There is no receiver because all instance of Message are publicly displayed.
 *
 * @see PrivateMessage for a direct communication message with a sender and a receiver.
 */
@Entity
public class Message implements Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String username;
    @NotNull
    private String message;
    @NotNull
	private Date date;


	public Message() {}

	public Message(String username, String message, Date date) {
		this.username = username;
		this.message = message;
		this.date = date;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date.toString();
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getFormattedDate(){
        return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(date);
    }


    @Override
    public Long getId() {
    	return  id;
    }


    @Override
    public boolean isNew() {
        return (null == username && null == message);
    }


    @Override
    public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
        }
        else if(!(obj instanceof Message)) {
	        return false;
        }

        Message other = (Message) obj;
	    if (date.getYear() == other.date.getYear() &&
                date.getMonth() == other.date.getMonth() &&
                date.getDay() == other.date.getDay() &&
                message.equals(other.message) &&
                username.equals(other.username)) {
	        return true;
        }
        else {
	        return false;
        }
    }


    @Override
    public String toString() {
	    return "Date: " + date + "\n" +
                "Message: " + message + "\n" +
                "Username: " + username;
    }
}