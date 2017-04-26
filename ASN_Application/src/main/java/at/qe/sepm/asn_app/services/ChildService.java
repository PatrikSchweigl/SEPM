package at.qe.sepm.asn_app.services;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.child.Sibling;
import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.models.ownExceptions.BirthdayConstraintException;
import at.qe.sepm.asn_app.models.ownExceptions.SiblingConstraintException;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.repositories.AuditLogRepository;
import at.qe.sepm.asn_app.repositories.ChildRepository;
import at.qe.sepm.asn_app.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//import java.util.Collection;
//import java.util.Date;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.03.2017
 */
@Component
@Scope("application")
public class ChildService {
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



    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    public Collection<Child> getAllChildren(){
        return childRepository.findAll();
    }




    @PreAuthorize("hasAuthority('ADMIN')")
    public Child saveChild(Child child) {
        this.child = child;
        //this.child.setUsername(this.child.getFirstName() + this.child.getLastName() + this.child.getId());

        /* Check whether or not the constraints are violated. */
        try {
            if(!checkConstraints()) {
                return null;
            }
        } catch (BirthdayConstraintException | SiblingConstraintException e) {
            e.printStackTrace();
        }

        return childRepository.save(child);
    }




    @PreAuthorize("hasAuthority('ADMIN') or principal.username eq #username")
    public Child loadUser(String username) {
        return null;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteChild(Child child) {
        AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(), "DELETED: "+ " [" + child.getParent1() + " & " + child.getParent2() + "]", null);
        auditLogRepository.save(log);
        childRepository.delete(child);

    }

    private UserData getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findFirstByUsername(auth.getName());
    }




    /**
     *
     * @return true iff no constraints are violated.
     */
    public boolean checkConstraints() throws BirthdayConstraintException, SiblingConstraintException {
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




    /**
     * A child may not be younger than 1/2 year and not older than 3 years.
     * @return true iff a child is between the age of 1/2 and 3 years.
     */
    public boolean checkBirthdayConstraints() throws BirthdayConstraintException {
        // This parsing is only needed because birthday is stored as a string.
        // TODO Change attribute birthday from String to LocalDateTime.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime birthdayTmp = LocalDateTime.parse(this.child.getBirthday() + " 00:00", formatter);
        System.out.println(birthdayTmp);

        long dateNow = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        long birthday = birthdayTmp.toEpochSecond(ZoneOffset.UTC);
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
     * Parents may not be the same person.
     * @return true iff no constraints regarding parents are violated.
     */
    public boolean checkParentsConstraints() {
        Parent p1 = new Parent();
        Parent p2 = new Parent();


        if (p1.equals(p2)) {
            System.out.println("equal");
            return false;
        }
        return true;
    }




    /**
     * Check whether or not one of the following constraints is violated:
     * A child may not be a sibling of itself.
     * A child can not have the same sibling twice or more.
     * @return iff no constraints are violated.
     */
    public boolean checkSiblingsConstraints() throws SiblingConstraintException {
        HashSet<Sibling> setSiblings = (HashSet) this.child.getListSiblings();

        // Check if the child is a sibling of itself.
        for (Sibling s : setSiblings) {
            if(s.equals(new Sibling(child.getFirstName(), child.getLastName(), child.getBirthday()))) {
                System.out.println("A child cannot be a sibling of itself");
                throw new SiblingConstraintException("A child cannot be a sibling of itself.");
            }
        }

        // Check if the child has the same sibling twice or more.
        for (Sibling s : setSiblings) {
            setSiblings.remove(s);  // Remove s from the set so we can iterate over the remaining siblings.

            for (Sibling s2 : setSiblings) {
                if (s.equals(s2)) {
                    System.out.println("A child cannot have the same sibling twice or more.");
                    throw new SiblingConstraintException("A child cannot have the same sibling twice or more.");
                }
            }
        }

        return true;
    }

}
