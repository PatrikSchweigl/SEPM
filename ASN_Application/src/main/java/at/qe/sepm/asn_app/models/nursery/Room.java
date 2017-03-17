package at.qe.sepm.asn_app.models.nursery;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.pedagogue.Pedagogue;
import at.qe.sepm.asn_app.models.trainee.Trainee;

import java.util.List;

/**
 * Created by zerus on 17.03.17.
 */
public class Room {
    private int maxStaff;
    private List<Pedagogue> listPedagogue;
    private List<Trainee> listTrainee;
    private List<Child> listChildren;
}
