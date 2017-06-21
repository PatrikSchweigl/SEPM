package at.qe.sepm.asn_app.ui.constraints;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.child.Sibling;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.ownExceptions.BirthdayConstraintException;
import at.qe.sepm.asn_app.ownExceptions.ParentConstraintException;
import at.qe.sepm.asn_app.ownExceptions.SiblingConstraintException;
import at.qe.sepm.asn_app.parser.BirthdayParser;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 08.05.17.
 * <p>
 * The main method of ChildConstraint checkConstraints(Child child) only returns true iff
 * none of the following constraints is violated:
 * - The child is between 1/2 and 3 years old.
 * - At least one parent of the child is an active user in the nursery.
 * - The parents of the child are not the same person.
 * - The child is no sibling of itself.
 * - The child does not have the same sibling twice or more.
 *
 * @see Parent
 * @see Sibling
 * @see BirthdayConstraintException
 * @see ParentConstraintException
 * @see SiblingConstraintException
 */
@Component
@Scope("application")
public class ChildConstraints {


    /**
     * Checks for the following constraints:
     * - A child may not be younger than 1/2 year and not older than 3 years.
     *
     * @return <code>true</code> iff a child is between the age of 1/2 and 3 years.
     */
    public static boolean checkBirthdayConstraints(Child child) throws BirthdayConstraintException {
        long birthday = BirthdayParser.parseBirthdayToLong(child.getBirthday());
        long dateNow = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        int ageDays = (int) ((dateNow - birthday) / 60 / 60 / 24);


        // Check if the child is younger than 0.5 year.
        if (ageDays < 0.5 * 365) {
            return false;
        }

        // Check if the child is older than 3 years.
        return ageDays <= (3 * 365);
    }


    /**
     * Checks for the following constraints:
     * - At least one parent must be registered in the nursery.
     * - Parents may not be the same person.
     *
     * @return <code>true</code> iff no constraints regarding parents are violated; <code>false</code> otherwise
     */
    public static boolean checkParentsConstraints(Child child) throws ParentConstraintException {
        //Parent p1 = child.getParent1();
        Parent p1 = child.getPrimaryParent();
        Parent p2 = child.getParent2();

        if (p1 == null && p2 == null) {
            return false;
        }

        return !p1.equals(p2);
    }


    /**
     * Check whether or not one of the following constraints is violated:
     * - A child may not be a sibling of itself.
     * - A child can not have the same sibling twice or more.
     *
     * @return <code>true</code> iff no constraints are violated.
     */
    public static boolean checkSiblingsConstraints(Child child) throws SiblingConstraintException {
        HashSet<Sibling> setSiblings = (HashSet) child.getSiblings();

        for (Sibling s : setSiblings) {

        }

        // Check if the child is a sibling of itself.
        for (Sibling s : setSiblings) {

            if (s.equals(new Sibling(child.getFirstName(), child.getLastName(), child.getBirthday()))) {
                return false;
            }
        }

        // Check if the child has the same sibling twice or more.
        for (Sibling s : setSiblings) {
            setSiblings.remove(s);  // Remove s from the set so we can iterate over the remaining siblings.

            for (Sibling s2 : setSiblings) {
                if (s.equals(s2)) {
                    return false;
                }
            }
        }

        return true;
    }

}