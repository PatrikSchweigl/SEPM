package at.qe.sepm.asn_app.models.nursery;

import at.qe.sepm.asn_app.models.child.Child;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by zerus on 17.03.17.
 *
 * Lunch holds information on which date a meal is available and how much it costs.
 * @see Child
 * @see Date
 */

@Entity
public class Lunch implements Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private Date date;
    @NotNull
    private String meal;
    private double cost;
    @OneToMany
    private List<Child> listChildren;


    public Lunch(Date date, String meal, double cost, List<Child> listChildren) {
        this.date = date;
        this.meal = meal;
        this.cost = cost;
        this.listChildren = listChildren;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public List<Child> getListChildren() {
        return listChildren;
    }

    public void setListChildren(List<Child> listChildren) {
        this.listChildren = listChildren;
    }


    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public boolean isNew() {
        return (null == date && null == meal);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof Lunch)) {
            return false;
        }

        Lunch other = (Lunch) obj;
        if (this.cost == other.cost &&
                this.date.equals(other.date) &&
                this.listChildren.equals(other.listChildren) &&
                this.meal.equals(other.meal)) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public String toString() {
        return "Cost: " + cost + "\n" +
                "Date: " + date + "\n" +
                "Children: " + listChildren + "\n" +
                "Meal: " + meal;
    }
}