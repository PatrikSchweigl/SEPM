package at.qe.sepm.asn_app.models.nursery;

import at.qe.sepm.asn_app.models.child.Child;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by zerus on 17.03.17.
 */

@Entity
public class Lunch implements Persistable<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private Date dayOfWeek;
    @NotNull
    private String meal;
    private double cost;
    @OneToMany
    private List<Child> listChildren;


    public Lunch(Date dayOfWeek, String meal, double cost, List<Child> listChildren) {
        this.dayOfWeek = dayOfWeek;
        this.meal = meal;
        this.cost = cost;
        this.listChildren = listChildren;
    }

    public Date getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Date dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
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
        return (null == dayOfWeek && null == meal);
    }
}

