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
    @Autowired
    private ChildRepository childRepository;
    @Autowired
    private AuditLogRepository auditLogRepository;
    @Autowired
    private UserRepository userRepository;

    private Child child;

    public void setChild(Child child) {
        this.child = child;
    }
    public Child getChild() {
        return this.child;
    }


    /*
    public Child saveChild(Child child) {
        this.child = child;
        //this.child.setUsername(this.child.getFirstName() + this.child.getLastName() + this.child.getId());

        // Check whether or not the constraints are violated.
        try {
            if(!checkConstraints()) {
                return null;
            }
        } catch (BirthdayConstraintException | ParentConstraintException | SiblingConstraintException e) {
            e.printStackTrace();
        }
        System.out.println("HHHHHHHHHHHHHHEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEREEEEEEEEEEEEEEEEEEEEEEEE111111");

        return childRepository.save(child);
    }
    */


    /**
     *
     * @return true iff no constraints are violated.
     */
    /*
    public boolean checkConstraints() throws BirthdayConstraintException, ParentConstraintException, SiblingConstraintException {
        if(!checkBirthdayConstraints()) {
            return false;   // Returning false here makes no sense since we throw an exception in the method.
        }
        else if(!checkParentsConstraints()) {
            return false;
        }
        else if(!checkSiblingsConstraints()) {
            return false;
        }
        return true;
    }
    */



    /**
     * A child may not be younger than 1/2 year and not older than 3 years.
     * @return true iff a child is between the age of 1/2 and 3 years.
     */
    public boolean checkBirthdayConstraints() throws BirthdayConstraintException {
        /*
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime birthdayTmp = LocalDateTime.parse(this.child.getBirthday() + " 00:00", formatter);
        System.out.println(birthdayTmp);
        long birthday = birthdayTmp.toEpochSecond(ZoneOffset.UTC);
        */
        long birthday = BirthdayParser.parseBirthdayToLong(this.child.getBirthday());
        long dateNow = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        int ageDays = (int)((dateNow-birthday)/60/60/24);
        System.out.println("date now: " + dateNow);
        System.out.println("birthday: " + birthday);
        System.out.println("age in days: " + ageDays);

        // Check if the child is younger than 0.5 year.
        if (ageDays < 0.5*365) {
            throw new BirthdayConstraintException("Too young");
        }

        // Check if the child is older than 3 years.
        if (ageDays > (3*365)) {
            throw new BirthdayConstraintException("Too old");
        }

        return true;
    }




    /**
     * At least one parent must be registered in the nursery.
     * Parents may not be the same person.
     * @return true iff no constraints regarding parents are violated.
     */
    /*
    public boolean checkParentsConstraints() throws ParentConstraintException {
        Parent p1 = child.getParent1();
        Parent p2 = child.getParent2();

        if (p1 == null && p2 == null) {
            throw new ParentConstraintException ("It is not allowed that both parents are null!");
        }

        if (p1.equals(p2)) {
            throw new ParentConstraintException("Parent1 and parent2 may not be equal!");
        }

        return true;
    }
    */



    /**
     * Check whether or not one of the following constraints is violated:
     * A child may not be a sibling of itself.
     * A child can not have the same sibling twice or more.
     * @return iff no constraints are violated.
     */
    /*
    public boolean checkSiblingsConstraints() throws SiblingConstraintException {
        HashSet<Sibling> setSiblings = (HashSet) this.child.getListSiblings();

        for(Sibling s : setSiblings) {
            System.out.println(s);
        }

        // Check if the child is a sibling of itself.
        for (Sibling s : setSiblings) {
            System.out.println(s.getFirstName());
            if(s.equals(new Sibling(child.getFirstName(), child.getLastName(), child.getBirthday()))) {
                throw new SiblingConstraintException("A child cannot be a sibling of itself.");
            }
        }

        // Check if the child has the same sibling twice or more.
        for (Sibling s : setSiblings) {
            setSiblings.remove(s);  // Remove s from the set so we can iterate over the remaining siblings.

            for (Sibling s2 : setSiblings) {
                if (s.equals(s2)) {
                    throw new SiblingConstraintException("A child cannot have the same sibling twice or more.");
                }
            }
        }

        return true;
    }
    */

}