package at.qe.sepm.asn_app.services;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.models.nursery.Lunch;
import at.qe.sepm.asn_app.repositories.AuditLogRepository;
import at.qe.sepm.asn_app.repositories.ChildRepository;
import at.qe.sepm.asn_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
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


    public Collection<Child> getAllChildren() {
        return childRepository.findAll();
    }


    public Child saveChild(Child child) {
        // Needed for JUnit because in that case no user is logged in.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            if (getAuthenticatedUser() != null) {
                AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(), "CREATED/CHANGED: " + child.getFirstName() + " " + child.getLastName(), new Date());
                auditLogRepository.save(log);
            }
        }
        child = childRepository.save(child);
        return child;
    }

    public Collection<Child> getChildrenByLunch(Lunch lunch) {
        Set<Child> ret = new HashSet<Child>();
        for (Long id : lunch.getChildrenIds()) {
            ret.add(loadChild(id));
        }
        return ret;
    }

    public Child loadChild(Long id) {
        return childRepository.findOne(id);
    }

    public void deleteChild(Child child) {
        // Needed for JUnit because in that case no user is logged in.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            if (getAuthenticatedUser() != null) {
                AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(), "DELETED: " + child.getFirstName() + " " + child.getLastName(), new Date());
                auditLogRepository.save(log);
            }
        }

        childRepository.delete(child);
    }

    public Collection<Child> getChildrenByParentUsername(String usrn) {
        return childRepository.getChildrenByParentUsername(usrn);
    }

    private UserData getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findFirstByUsername(auth.getName());
    }

    public Child getChildrenByFirstnameAndParentUsername(String username, String childFirstname) {
        return childRepository.getChildrenByFirstnameAndParentUsername(username, childFirstname);
    }


}
