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
 * AuditLog contains information about changes made to the database. This includes the following:
 * - Create a person
 * - Delete a person
 * - Upload a picture
 *
 * @see Picture
 * @see at.qe.sepm.asn_app.models.child.Child
 * @see at.qe.sepm.asn_app.models.employee.Employee
 * @see at.qe.sepm.asn_app.models.referencePerson.Parent
 */
@Entity
public class AuditLog implements Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String userName;
    @NotNull
    private String log;
    @NotNull
    private Date date;


    public AuditLog(){}

    public AuditLog(String userName, String log, Date date) {
        this.userName = userName;
        this.log = log;
        this.date = date;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Date getDate() {
        return date;
    }
    
    public String getFormattedOriginDate(){
    	return new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(date);
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
        return (null == userName);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        else if (obj == null) {
            return false;
        }
        else if (!(obj instanceof AuditLog)) {
            return false;
        }

        AuditLog other = (AuditLog) obj;
        if (id.equals(other.id)) {
            return true;
        }

        return (date.getYear() == other.date.getYear() &&
                date.getMonth() == other.date.getMonth() &&
                date.getDay() == other.date.getDay() &&
                log.equals(other.log) &&
                userName.equals(other.userName));
    }


    @Override
    public String toString() {
        return "Date: " + date + "\n" +
                "Log: " + log + "\n" +
                "Username: " + userName;
    }
}
