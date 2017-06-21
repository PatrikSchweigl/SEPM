package at.qe.sepm.asn_app.ownExceptions;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 26.04.17.
 * <p>
 * This exception gets thrown if one of the following birthday constraints is violated:
 * - A child has no parents.
 * - The parents of a child are the same person.
 *
 * @see at.qe.sepm.asn_app.ui.constraints.ChildConstraints
 */
public class ParentConstraintException extends Exception {

}
