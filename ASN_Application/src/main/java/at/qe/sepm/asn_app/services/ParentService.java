package at.qe.sepm.asn_app.services;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.repositories.AuditLogRepository;
import at.qe.sepm.asn_app.repositories.ParentRepository;
import at.qe.sepm.asn_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
    @Autowired
    private MailService mailService;
    @Autowired
    private UserService userService;


    public Collection<Parent> getAllParents() {
        return parentRepository.findAll();
    }


    public Parent saveParent(Parent parent) {
        // Needed for JUnit because in that case no user is logged in.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            if (getAuthenticatedUser() != null) {
                AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(), "CREATED/CHANGED: " + parent.getUsername() + " [" + parent.getUserRole() + "]", new Date());
                auditLogRepository.save(log);
            }
        }
        String pwd = userService.generatePasswordNew(parent.getEmail());
        mailService.sendEmail(parent.getEmail(), "Care4Fun-App - Registrierung",
                "Willkommen bei Care4Fun-Application!\n\n" +
                        "Die Plattform der Kinderkrippe erreichen Sie via localhost:8080.\n\n" +
                        "Ihr Benutzername: " + parent.getUsername() + "\n" +
                        "Ihr Passwort:     " + pwd +
                        "\n\n\n" +
                        "Sollten Probleme auftreten, bitte umgehend beim Administrator melden.\n\n" +
                        "Viel Spaß wünschen das Kinderkrippen-Team!");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        parent.setPassword(passwordEncoder.encode(pwd));
        parent.setUserRole(UserRole.PARENT);
        return parentRepository.save(parent);
    }

    public Parent updateParent(Parent parent, Child child) {
        mailService.sendEmail(parent.getEmail(), "Care4Fun-App - Kind Abmeldung",
                "Guten Tag " + parent.getFirstName() + " " + parent.getLastName() +
                        "\n\nIhr Kind (" + child.getFirstName() + " " + child.getLastName() + ") wurde von unserer" +
                        " Kinderkrippe abgemeldet. Desweiteren wurde der diesbezügliche Datensatz (inkl. täglicher Anmeldungen und Essensanmeldungen)" +
                        " aus unserem System entfernt. Sollten Sie nur dieses Kind angemeldet haben, wird Ihr Status auf INAKTIV gesetzt und Sie genießen" +
                        " nur noch beschränkte Zugriffsrechte." +
                        "\n\nSollten Probleme auftreten, bitte umgehend beim Administrator melden.\n\n" +
                        "Liebe Grüße wünscht das Kinderkrippen-Team!");
        return parentRepository.save(parent);
    }

    public Parent changeStatus(Parent parent, Boolean status) {
        parent.setStatus(status);
        return parentRepository.save(parent);
    }


    public Parent loadParent(String username) {
        return parentRepository.findFirstByUsername(username);
    }


    public void deleteParent(Parent parent) {
        // Needed for JUnit because in that case no user is logged in.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(), "DELETED: " + parent.getUsername() + " [" + parent.getUserRole() + "]", new Date());
            auditLogRepository.save(log);
        }
        parentRepository.delete(parent);
    }

    private UserData getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findFirstByUsername(auth.getName());
    }

    public void changePassword(String password) {
        UserData user = getAuthenticatedUser();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }


    public void resetPassword(Parent parent) {
        AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(), "RESET PASSWD: " + parent.getUsername() + " [" + parent.getUserRole() + "]", new Date());
        auditLogRepository.save(log);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        parent.setPassword(passwordEncoder.encode("passwd"));
        parentRepository.save(parent);
    }


}
