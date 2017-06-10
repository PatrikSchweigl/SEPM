package at.qe.sepm.asn_app.services;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.repositories.AuditLogRepository;
import at.qe.sepm.asn_app.repositories.UserRepository;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * Service for accessing and manipulating user data.
 *
 * @author Michael Brunner <Michael.Brunner@uibk.ac.at>
 */
@Component
@Scope("application")
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuditLogService auditLogService;

    /**
     * Returns a collection of all users.
     *
     * @return
     */
    public Collection<UserData> getAllUsers() {
        return userRepository.findAll();
    }

    public Collection<UserData> getAllAdmin() {
        return userRepository.findAllAdmin();
    }


    public Collection<UserData> getParentsByNotification(){
        try {
            return userRepository.findParentsByNotification();
        }catch(NullPointerException ex){
            return null;
        }
    }

    /**
     * Loads a single user identified by its username.
     *
     * @param username the username to search for
     * @return the user with the given username
     */
    public UserData loadUser(String username) {
        return userRepository.findFirstByUsername(username);
    }

    public UserData saveUser(UserData userData) {
        AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(),"SAVED: " + userData.getUsername() + " [" + userData.getUserRole() + "] ", new Date());
        auditLogService.saveAuditLog(log);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userData.setPassword( passwordEncoder.encode("passwd"));
        userData.setUserRole(UserRole.ADMIN);
        return userRepository.save(userData);
    }


    public UserData changeData(UserData userData) {
        AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(),"CHANGED: " + userData.getUsername() + " [" + userData.getUserRole() + "] ", new Date());
        auditLogService.saveAuditLog(log);
        return userRepository.save(userData);
    }

    /**
     * Deletes the userData.
     *
     * @param userData the userData to delete
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(UserData userData) {
        AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(), "DELETED: "+ userData.getUsername() + " [" + userData.getUserRole() +"]", new Date());
        auditLogService.saveAuditLog(log);
        userRepository.delete(userData);
    }


    public UserData getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findFirstByUsername(auth.getName());
    }

}
