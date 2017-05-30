package at.qe.sepm.asn_app.models.nursery;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.utils.DateUtils;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 17.03.17.
 *
 * Lunch holds information on which date a meal is available and how much it costs.
 * Additionally Lunch contains a list of all children (as ids) who are signed up for it.
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
    private String date;
    @NotNull
    private String meal;
    private double cost;
    @ElementCollection(targetClass = Long.class)
    private Set<Long> childrenIds;


    public Lunch(String date, String meal, double cost) {
        this.date = date;
        this.meal = meal;
        this.cost = cost;
    }
    public Lunch(){}

    public void addChild(Long id){
        childrenIds.add(id);
    }
    public void addChild(Child c){
        addChild(c.getId());
    }

    public void removeChild(Long id){
        childrenIds.remove(id);
    }

    public void removeChild(Child c){
        removeChild(c.getId());
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = DateUtils.primefaceCalendarToStr(date);
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

    public int getNumChildren() {
        return childrenIds.size();
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
                "Children: " + childrenIds + "\n" +
                "Meal: " + meal;
    }
}