package at.qe.sepm.asn_app.models.nursery;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.utils.DateUtils;
import org.springframework.data.domain.Persistable;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
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
    @Expose
    private Long id;
    @NotNull
    @Expose
    private Date date;
    @NotNull
    @Expose
    private String meal;
    @NotNull
    @Expose
    private double cost;
    @ElementCollection(targetClass = Long.class, fetch = FetchType.EAGER)
    private Set<Long> childrenIds;



    public Lunch(Date date, String meal, double cost) {
        this.date = date;
        this.meal = meal;
        this.cost = cost;
    }
    public Lunch(){}

    /** addChild
     *  adds child to lunch
     * @param id Child or Long (id)
     */
    public void addChild(Long id){
        childrenIds.add(id);
    }
    public void addChild(Child c){
        addChild(c.getId());
    }

    /** removeChild
     *  removes a child from lunch
     * @param id Child or Long(id)
     */

    public void removeChild(Long id){
        childrenIds.remove(id);
    }

    public void removeChild(Child c){
        removeChild(c.getId());
    }

    public Date getDate() {
        return date;
    }
    
    public String getFormattedOriginDate(){
    	return new SimpleDateFormat("dd-MM-yyyy").format(date);
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

    public int getNumChildren() {
        return childrenIds.size();
    }
    public Set<Long> getChildrenIds(){
        return childrenIds;
    }
    public void setChildrenIds(Set<Long> childrenIds) {
        this.childrenIds = childrenIds;
    }

    @Override
    public Long getId() {
        return id;
        //return new Long(id);
    }

    public void setId(Long id) {
        this.id = id;
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
        return this.cost == other.cost &&
                (date.compareTo(other.date) == 0) &&
                this.meal.equals(other.meal);
    }


    @Override
    public String toString() {
        return "Cost: " + cost + "\n" +
                "Date: " + date + "\n" +
                "Children: " + childrenIds + "\n" +
                "Meal: " + meal;
    }
}