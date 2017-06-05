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
    private int id;
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

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public boolean isNew() {
        return (null == userName);
    }
}
