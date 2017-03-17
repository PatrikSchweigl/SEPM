package at.qe.sepm.asn_app.models.nursery;

import at.qe.sepm.asn_app.models.child.Child;

import java.time.DayOfWeek;
import java.util.List;

/**
 * Created by zerus on 17.03.17.
 */
public class Lunch {
    private DayOfWeek dayOfWeek;
    private String meal;
    private double cost;
    private List<Child> listChildren;
}
