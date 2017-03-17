package at.qe.sepm.asn_app.models.parent;



import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.general.Person;

import java.util.List;

/**
 * Created by zerus on 17.03.17.
 */
public class Parent extends Person {
    private List<Child> listChildren;
    private List<Caregiver> listCaregivers;
    private List<Assignment> listAssignments;
}
