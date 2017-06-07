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
 * on 14.05.2017
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


    public Collection<Parent> getAllParents(){
        return parentRepository.findAll();
    }


    public Parent saveParent(Parent parent){
        // Needed for JUnit because in that case no user is logged in.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(), "CREATED/CHANGED: " + parent.getUsername() + " [" + parent.getUserRole() + "]", new Date());
            auditLogRepository.save(log);
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        parent.setPassword(passwordEncoder.encode(parent.getPassword()));
        parent.setUserRole(UserRole.PARENT);
        return parentRepository.save(parent);
    }

    public Parent changeStatus(Parent parent, Boolean status){
        parent.setStatus(status);
        return parentRepository.save(parent);
    }


    public Parent loadParent(String username){
        return parentRepository.findFirstByUsername(username);
    }


    public void deleteParent(Parent parent){
        // Needed for JUnit because in that case no user is logged in.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(), "DELETED: " + parent.getUsername() + " [" + parent.getUserRole() + "]", new Date());
            auditLogRepository.save(log);
        }
        parentRepository.delete(parent);
    }

    private UserData getAuthenticatedUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findFirstByUsername(auth.getName());
    }

    public void changePassword(String password){
        UserData user = getAuthenticatedUser();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }


    public void resetPassword(Parent parent){
        AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(), "RESET PASSWD: "+ parent.getUsername() + " [" + parent.getUserRole() +"]", new Date());
        auditLogRepository.save(log);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        parent.setPassword(passwordEncoder.encode("passwd"));
        parentRepository.save(parent);
    }




}
