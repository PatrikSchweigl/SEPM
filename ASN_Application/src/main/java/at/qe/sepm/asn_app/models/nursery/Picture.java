package at.qe.sepm.asn_app.models.nursery;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.general.Comment;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 02.05.2017
 */

@Entity
public class Picture implements Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String url;
    @ManyToOne(optional = false)
    private UserData publisher;
    @OneToMany
    @ElementCollection
    private Set<Comment> comment;
    private Date date;
    private String title;


    public Picture() {}

    public Picture(String url, UserData publisher, Date date, String title) {
        this.url = url;
        this.publisher = publisher;
        this.date = date;
        this.title = title;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UserData getPublisher() {
        return publisher;
    }

    public void setPublisher(UserData publisher) {
        this.publisher = publisher;
    }

    public Set<Comment> getComment() {
        return comment;
    }

    public void setComment(Set<Comment> comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public boolean isNew() {
        return (url.equals(""));
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof Picture)) {
            return false;
        }

        Picture other = (Picture) obj;
        if (this.comment.equals(other.comment) &&
                this.date.equals(other.date) &&
                this.publisher.equals(other.publisher) &&
                this.title.equals(other.title) &&
                this.url.equals(other.url)) {
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public String toString() {
        return "Date: " + date + "\n" +
                "Title: " + title + "\n" +
                "Publisher: " + publisher + "\n" +
                "Comment: " + comment + "\n" +
                "URL: " + url;
    }
}
