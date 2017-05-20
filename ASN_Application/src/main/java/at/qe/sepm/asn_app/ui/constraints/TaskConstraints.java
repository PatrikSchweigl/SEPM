package at.qe.sepm.asn_app.ui.constraints;

import at.qe.sepm.asn_app.models.nursery.Task;
import at.qe.sepm.asn_app.ownExceptions.TaskConstraintException;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 20.05.17 12:57 CEST.
 */
public class TaskConstraints {

    public static boolean checkTaskConstraints(Task task) {
        try {
            if (!checkParentConstraints(task)) {
                throw new TaskConstraintException("Task parent constraints are violated.");
            }
            else if(!checkDateConstraints(task)) {
                throw new TaskConstraintException("Task date constraints are violated.");
            }
        }
        catch(TaskConstraintException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    public static boolean checkParentConstraints(Task task) {
        return true;
    }


    public static boolean checkDateConstraints(Task task) {
        return true;
    }
}
