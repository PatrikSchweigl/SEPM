package at.qe.sepm.asn_app.models.general;

import at.qe.sepm.asn_app.models.UserData;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 02.05.2017
 */
@Entity
public class Comment implements Persistable<Long>{

    private static final long serialVersionUID = 1L;

    @Id
    private int id;
    private String comment;
    private Date date;
    @ManyToOne(optional = false)
    private UserData publisher;

    public Comment(int id, String comment, Date date, UserData publisher) {
        this.id = id;
        this.comment = comment;
        this.date = date;
        this.publisher = publisher;
    }

    public Comment() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserData getPublisher() {
        return publisher;
    }

    public void setPublisher(UserData publisher) {
        this.publisher = publisher;
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isNew() {
        return (comment.equals(""));
    }
}
