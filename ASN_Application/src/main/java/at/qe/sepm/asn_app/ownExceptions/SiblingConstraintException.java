package at.qe.sepm.asn_app.ownExceptions;


/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 22.04.17.
 *
 * This exception gets thrown if one of the following birthday constraints is violated:
 * - A child is a sibling of itself.
 * - A child has the same sibling twice or more.
 * @see at.qe.sepm.asn_app.ui.constraints.ChildConstraints
 */
public class SiblingConstraintException extends Exception {

    public SiblingConstraintException() {
        super();
    }

    public SiblingConstraintException(String message) {
        super(message);
    }

}