package at.qe.sepm.asn_app.services;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.employee.Employee;
import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.repositories.AuditLogRepository;
import at.qe.sepm.asn_app.repositories.EmployeeRepository;
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
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AuditLogRepository auditLogRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;

    public Collection<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Employee saveEmployee(Employee employee) {
        // Needed for JUnit because in that case no user is logged in.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(), "CREATED/CHANGED: " + employee.getUsername() + " [" + employee.getUserRole() + "]", new Date());
            auditLogRepository.save(log);
        }
    	String pwd = userService.generatePasswordNew(employee.getEmail());
        mailService.sendEmail(employee.getEmail(), "Care4Fun-App - Registrierung",
                "Willkommen bei Care4Fun-Application!\n\n" +
                        "Die Plattform der Kinderkrippe erreichen Sie via localhost:8080.\n\n" +
                        "Ihr Benutzername: " + employee.getUsername() + "\n" +
                        "Ihr Passwort: " + pwd +
                        "\n\nBitte ändern Sie nach dem ersten Login Ihr Password.\n" +
                        "Sollten Probleme auftauchen, bitte umgehend beim Administrator melden.\n\n" +
                        "Viel Spaß wünscht das Kinderkrippen-Team!");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        employee.setPassword(passwordEncoder.encode(pwd));
        employee.setUserRole(UserRole.EMPLOYEE);

        return employeeRepository.save(employee);
    }

    public Employee loadEmployee(String username) {
        return employeeRepository.findFirstByUsername(username);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteEmployee(Employee employee) {
        // Needed for JUnit because in that case no user is logged in.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(), "DELETED: " + employee.getUsername() + " [" + employee.getUserRole() + "]", new Date());
            auditLogRepository.save(log);
        }
        employeeRepository.delete(employee);

    }

    private UserData getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findFirstByUsername(auth.getName());
    }

    public void changePassword(String password){
        UserData user = getAuthenticatedUser();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public void resetPassword(Employee employee){
        // Needed for JUnit because in that case no user is logged in.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(), "RESET PASSWD: " + employee.getUsername() + " [" + employee.getUserRole() + "]", new Date());
            auditLogRepository.save(log);
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        employee.setPassword(passwordEncoder.encode("passwd"));
        employeeRepository.save(employee);
    }
}
