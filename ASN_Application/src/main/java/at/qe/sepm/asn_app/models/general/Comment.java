package at.qe.sepm.asn_app.models.general;

import org.springframework.data.domain.Persistable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import java.text.SimpleDateFormat;
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
@Transactional
public class Comment implements Persistable<Long>{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String comment;
    @NotNull
    private Date date;
    @NotNull
    private String username;
    private String pictureName;


    public Comment() {}

    public Comment(Long id, String comment, Date date, String username) {
        //this.id = id;
        this.comment = comment;
        this.date = date;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getFormattedDate(){
        return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(date);
    }


    @Override
    public Long getId() {
        return id;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                this.date.getYear() == other.date.getYear() &&
                this.date.getMonth() == other.date.getMonth() &&
                this.date.getDay() == other.date.getDay() &&
                this.username.equals(other.username)) {
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
                "Publisher: " + username;
    }

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}
}
