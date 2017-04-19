package at.qe.sepm.asn_app.services;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.repositories.AuditLogRepository;
import at.qe.sepm.asn_app.repositories.ParentRepository;
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
public class ParentService {

    @Autowired
    private ParentRepository parentRepository;
    @Autowired
    private AuditLogRepository auditLogRepository;
    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    public Collection<Parent> getAllParents() {
         return parentRepository.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Parent saveParent(Parent parent) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        parent.setPassword(passwordEncoder.encode(parent.getPassword()));
        parent.setUserRole(UserRole.PARENT);

        return parentRepository.save(parent);
    }

    @PreAuthorize("hasAuthority('ADMIN') or principal.username eq #username")
    public Parent loadUser(String username) {
        return parentRepository.findFirstByUsername(username);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteParent(Parent parent) {
        AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(), "DELETED: "+ parent.getUsername() + " [" + parent.getUserRole() +"]", new Date());
        auditLogRepository.save(log);
        parentRepository.delete(parent);
    }

    private UserData getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findFirstByUsername(auth.getName());
    }

}
