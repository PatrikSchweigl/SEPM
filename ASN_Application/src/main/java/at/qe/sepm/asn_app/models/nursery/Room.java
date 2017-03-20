package at.qe.sepm.asn_app.models.nursery;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.employee.Employee;

import java.util.List;

/**
 * Created by zerus on 17.03.17.
 */
public class Room {

    private static final long serialVersionUID = 1L;

    private int maxStaff;
    private List<Employee> listEmployee;
    private List<Child> listChildren;
}
