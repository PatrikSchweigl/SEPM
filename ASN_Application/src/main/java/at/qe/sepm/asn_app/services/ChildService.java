package at.qe.sepm.asn_app.services;

import at.qe.sepm.asn_app.models.User;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.repositories.AuditLogRepository;
import at.qe.sepm.asn_app.repositories.ChildRepository;
import at.qe.sepm.asn_app.repositories.UserRepository;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

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



    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    public Collection<Child> getAllChilds(){
        return childRepository.findAll();
    }




    @PreAuthorize("hasAuthority('ADMIN')")
    public Child saveChild(Child child) {
        this.child = child;

        /* Check whether or not the constraints are violated. */
        if(!checkConstraints()) {
            return null;
        }

        return childRepository.save(child);
    }




    @PreAuthorize("hasAuthority('ADMIN') or principal.username eq #username")
    public Child loadUser(String username) {
        return childRepository.findFirstByUsername(username);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteChild(Child child) {
        AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(), "DELETED: "+ child.getUsername() + " [" + child.getParent1() + " & " + child.getParent2() + "]", new Date());
        auditLogRepository.save(log);
        childRepository.delete(child);

    }

    private User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findFirstByUsername(auth.getName());
    }


    /**
     *
     * @return true if no constraints are violated; false if at least one constraint is violated
     */
    public boolean checkConstraints() {
        System.out.println("hi");
        DateTime dateTimeBirthday = parseBirthday();
        long dateNow = DateTime.now().getMillis();
        long birthday = dateTimeBirthday.getMillis();

        System.out.println(dateNow);
        System.out.println(birthday);

        /* Check if the child is younger than 1/2 year.
         * A cast ist needed because the values are too big. */
        if ((dateNow-birthday) < (long) 1/2*365*24*60*60*1000) {
            return false;
        }

        /* Check if the child is older than 3 years. */
        if ((dateNow-birthday) > (long)3*365*24*60*60*1000) {
            System.out.println((dateNow-birthday)>3*365*24*60*60*1000);
            return false;
        }

        return true;
    }



    public DateTime parseBirthday() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        return dateTimeFormatter.parseDateTime(child.getBirthday());
        //child.setBirthday(dateTime);
    }
}
