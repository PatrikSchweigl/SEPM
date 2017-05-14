package at.qe.sepm.asn_app.models.nursery;

import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by zerus on 17.03.17.
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

	public Message() {

	}

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
	    if (this.date.equals(other.date) &&
                this.message.equals(other.message) &&
                this.username.equals(other.username)) {
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
