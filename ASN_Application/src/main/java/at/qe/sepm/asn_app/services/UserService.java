package at.qe.sepm.asn_app.services;

import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.repositories.AuditLogRepository;
import at.qe.sepm.asn_app.repositories.UserRepository;
import at.qe.sepm.asn_app.models.User;

import java.util.Collection;
import java.util.Date;

import ch.qos.logback.core.joran.action.ActionUtil;
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
    private AuditLogRepository auditLogRepository;

    /**
     * Returns a collection of all users.
     *
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public Collection<User> getAllUsers() {
        return userRepository.findAllAdmin();
    }

    /**
     * Loads a single user identified by its username.
     *
     * @param username the username to search for
     * @return the user with the given username
     */
    @PreAuthorize("hasAuthority('ADMIN') or principal.username eq #username")
    public User loadUser(String username) {
        return userRepository.findFirstByUsername(username);
    }

    /**
     * Saves the user.
     *
     * @param user the user to save
     * @return the updated user
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public User saveUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword( passwordEncoder.encode(user.getPassword()));
        user.setUserRole(UserRole.ADMIN);
        return userRepository.save(user);
    }

    /**
     * Deletes the user.
     *
     * @param user the user to delete
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(User user) {
        AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(), "DELETED: "+ user.getUsername() + " [" + user.getUserRole() +"]", new Date());
        auditLogRepository.save(log);
        userRepository.delete(user);
    }

    private User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findFirstByUsername(auth.getName());
    }

}
