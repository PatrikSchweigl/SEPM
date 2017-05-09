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
public class PrivateMessage implements Persistable<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String usernameSender;

    private String usernameReceiver;

    private String message;

	private Date date;

	public PrivateMessage() {

	}

	public PrivateMessage(String usernameSender, String usernameReceiver, String message, Date date) {
		this.usernameSender = usernameSender;
		this.usernameReceiver = usernameReceiver;
		this.message = message;
		this.date = date;
	}

	public String getUsernameReceiver() {
		return usernameReceiver;
	}

	public void setUsername(String usernameReceiver) {
		this.usernameReceiver = usernameReceiver;
    }
	
	public String getUsernameSender() {
		return usernameReceiver;
	}

	public void setUsernameSender(String usernameSender) {
		this.usernameSender = usernameSender;
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
        return (null == usernameSender && null == message);
    }
}
