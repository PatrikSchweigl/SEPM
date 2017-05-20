package at.qe.sepm.asn_app.ui.constraints;

import at.qe.sepm.asn_app.models.nursery.Task;
import at.qe.sepm.asn_app.ownExceptions.TaskConstraintException;

import java.util.Date;

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


    /**
     * Begin- and ending date of task must fulfill the following conditions:
     * - The begin date must be after the current date.
     * - The begin date must before the ending date.
     * - They must not be equal.
     * @param task The Task to be checked.
     * @return false if the begin date is before the current date or if the begin date is after the ending date.
     */
    public static boolean checkDateConstraints(Task task) {
        if (task.getBeginDate().before(new Date()) ||
                task.getBeginDate().after(task.getEndingDate()) ||
                task.getBeginDate().equals(task.getEndingDate())) {
            return false;
        }
        return true;
    }
}
