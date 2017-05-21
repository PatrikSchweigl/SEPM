package at.qe.sepm.asn_app.ownExceptions;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 22.04.17.
 *
 * This exception gets thrown if one of the following birthday constraints is violated:
 * - A child is younger than 1/2 year.
 * - A child is older than 3 years.
 * - A parent is younger than 14 years.
 * - A parent is older than 99 years.
 * @see at.qe.sepm.asn_app.ui.constraints.ChildConstraints
 * @see at.qe.sepm.asn_app.ui.constraints.ParentConstraints
 */
public class BirthdayConstraintException extends Exception {

    public BirthdayConstraintException() {
        super();
    }

    public BirthdayConstraintException(String message) {
        super(message);
    }

}
