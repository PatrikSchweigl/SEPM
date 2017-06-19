package at.qe.sepm.c4f_app.ownExceptions;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 13.05.17.
 *
 * This exception gets thrown if one of the following birthday constraints is violated:
 * - The password is too short.
 * - The password is not complex enough.
 * - (For parents only): The password contains the name of a child of the parent as a substring.
 * @see at.qe.sepm.c4f_app.ui.constraints.PasswordConstraints
 */
public class PasswordConstraintException extends Exception {

    public PasswordConstraintException() {
        super();
    }

    public PasswordConstraintException(String message) {
        super(message);
    }

}
