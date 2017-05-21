package at.qe.sepm.asn_app.models.referencePerson;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 19.03.2017
 *
 * This class specifies in what relationship a caregiver is with a child.
 * A caregiver is only needed if the parents of a child are not available.
 * @see Caregiver
 */
public enum Relationship {
    AUNT_UNCLE,
    GRANDPARENT,
    FRIEND_OF_THE_FAMILY,
    SIBLING
}