package at.qe.sepm.asn_app.services;

import at.qe.sepm.asn_app.models.User;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.nursery.AuditLog;
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




    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    public Collection<Child> getAllChilds(){
        return childRepository.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Child saveChild(Child child) {
        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //child.setPassword(passwordEncoder.encode(child.getPassword()));
        //child.setUserRole(UserRole.EMPLOYEE);

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
}
