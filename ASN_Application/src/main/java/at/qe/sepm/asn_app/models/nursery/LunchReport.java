package at.qe.sepm.asn_app.models.nursery;

import at.qe.sepm.asn_app.models.child.Child;

/**
 * Created by Auki on 13.06.2017.
 */
public class LunchReport {
    private Lunch lunch;
    private Child child;

    public LunchReport(Lunch lunch, Child child){
        this.lunch = lunch;
        this.child = child;
    }

    public Lunch getLunch() {
        return lunch;
    }

    public void setLunch(Lunch lunch) {
        this.lunch = lunch;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }
}
