package at.qe.sepm.c4f_app.ownExceptions;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 20.05.17 12:55 CEST.
 */
public class TaskConstraintException extends Exception {

    public TaskConstraintException() {}

    public TaskConstraintException(String message) {
        super(message);
    }
}
