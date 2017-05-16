package at.qe.sepm.asn_app.ui.constraints;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.child.Sibling;
import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.ownExceptions.BirthdayConstraintException;
import at.qe.sepm.asn_app.ownExceptions.ParentConstraintException;
import at.qe.sepm.asn_app.ownExceptions.SiblingConstraintException;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.parser.BirthdayParser;
import at.qe.sepm.asn_app.repositories.AuditLogRepository;
import at.qe.sepm.asn_app.repositories.ChildRepository;
import at.qe.sepm.asn_app.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by zerus on 08.05.17.
 */

@Component
@Scope("application")
public class ChildConstraints {

    /**
     *
     * @return true iff no constraints are violated.
     */
    public static boolean checkConstraints(Child child) throws BirthdayConstraintException, ParentConstraintException, SiblingConstraintException {
        if(!checkBirthdayConstraints(child)) {
            return false;   // Returning false here makes no sense since we throw an exception in the method.
        }
        else if(!checkParentsConstraints(child)) {
            return false;
        }
        else if(!checkSiblingsConstraints(child)) {
            return false;
        }
        return true;
    }


    /**
     * A child may not be younger than 1/2 year and not older than 3 years.
     * @return <code>true</code> iff a child is between the age of 1/2 and 3 years.
     */
    public static boolean checkBirthdayConstraints(Child child) throws BirthdayConstraintException {
        long birthday = BirthdayParser.parseBirthdayToLong(child.getBirthday());
        long dateNow = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        int ageDays = (int)((dateNow-birthday)/60/60/24);
        System.out.println("date now: " + dateNow);
        System.out.println("birthday: " + birthday);
        System.out.println("age in days: " + ageDays);

        // Check if the child is younger than 0.5 year.
        if (ageDays < 0.5*365) {
            return false;
        }

        // Check if the child is older than 3 years.
        if (ageDays > (3*365)) {
            return false;
        }

        return true;
    }


    /**
     * At least one parent must be registered in the nursery.
     * Parents may not be the same person.
     * @return <code>true</code> iff no constraints regarding parents are violated.
     */
    public static boolean checkParentsConstraints(Child child) throws ParentConstraintException {
        //Parent p1 = child.getParent1();
        Parent p1 = child.getPrimaryParent();
        Parent p2 = child.getParent2();

        if (p1 == null && p2 == null) {
            return false;
        }

        if (p1.equals(p2)) {
            return false;
        }

        return true;
    }


    /**
     * Check whether or not one of the following constraints is violated:
     * A child may not be a sibling of itself.
     * A child can not have the same sibling twice or more.
     * @return <code>true</code> iff no constraints are violated.
     */
    public static boolean checkSiblingsConstraints(Child child) throws SiblingConstraintException {
        HashSet<Sibling> setSiblings = (HashSet) child.getSiblings();

        for(Sibling s : setSiblings) {
            System.out.println(s);
        }

        // Check if the child is a sibling of itself.
        for (Sibling s : setSiblings) {
            System.out.println(s.getFirstName());
            if(s.equals(new Sibling(child.getFirstName(), child.getLastName(), child.getBirthday()))) {
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