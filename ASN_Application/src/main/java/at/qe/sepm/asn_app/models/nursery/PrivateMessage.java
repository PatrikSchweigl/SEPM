package at.qe.sepm.asn_app.models.nursery;

import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 17.03.17.
 * <p>
 * A PrivateMessage contains the name of the sender, the receiver, the message itself
 * and a date on which the message got sent. The difference to a normal {@link Message}
 * is that a PrivateMessage is not broadcasted, but has exactly one receiver.
 *
 * @see Message for a publicly displayed broadcast message with one sender and multiple receivers.
 */
@Entity
public class PrivateMessage implements Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String usernameSender;
    @NotNull
    private String usernameReceiver;
    @NotNull
    private String message;
    private Date date;


    public PrivateMessage() {
    }


    public String getUsernameReceiver() {
        return usernameReceiver;
    }

    public void setUsernameReceiver(String usernameReceiver) {
        this.usernameReceiver = usernameReceiver;
    }

    public String getUsernameSender() {
        return usernameSender;
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
        return id;
    }


    @Override
    public boolean isNew() {
        return (null == usernameSender && null == message);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof PrivateMessage)) {
            return false;
        }

        PrivateMessage other = (PrivateMessage) obj;
        return date.getYear() == other.date.getYear() &&
                date.getMonth() == other.date.getMonth() &&
                date.getDay() == other.date.getDay() &&

                this.message.equals(other.message) &&
                this.usernameReceiver.equals(other.usernameReceiver) &&
                this.usernameSender.equals(other.usernameSender);
    }


    @Override
    public String toString() {
        return "Date: " + date + "\n" +
                "Message: " + message + "\n" +
                "UsernameReceiver: " + usernameReceiver + "\n" +
                "UsernameSender: " + usernameSender;
    }
}
