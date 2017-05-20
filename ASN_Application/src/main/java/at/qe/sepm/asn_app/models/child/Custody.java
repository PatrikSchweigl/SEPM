package at.qe.sepm.asn_app.models.child;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 19.03.2017
 *
 * Custody specifies which Parent is responsible for a child.
 * @see Child
 * @see at.qe.sepm.asn_app.models.referencePerson.Parent
 */
public enum Custody {
    FATHER,
    MOTHER,
    BOTH
}
