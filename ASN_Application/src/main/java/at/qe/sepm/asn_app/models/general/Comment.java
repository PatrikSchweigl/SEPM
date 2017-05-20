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
 *
 * A comment can be posted under a Picture. Similar to Picture a comment has a publish date and a publisher,
 * but it also contains the content of the comment as a String.
 * @see at.qe.sepm.asn_app.models.nursery.Picture
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


    public Comment() {}

    public Comment(int id, String comment, Date date, UserData publisher) {
        this.id = id;
        this.comment = comment;
        this.date = date;
        this.publisher = publisher;
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


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        else if (!(obj instanceof Comment)) {
            return false;
        }

        Comment other = (Comment) obj;
        if (this.comment.equals(other.comment) &&
                this.date.equals(other.date) &&
                this.publisher.equals(other.publisher)) {
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public String toString() {
        return "Comment: " + comment + "\n" +
                "Date: " + date + "\n" +
                "Publisher: " + publisher;
    }
}
