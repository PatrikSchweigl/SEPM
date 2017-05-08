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




    public Collection<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Employee saveEmployee(Employee employee) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setUserRole(UserRole.EMPLOYEE);

        return employeeRepository.save(employee);
    }

    public Employee loadUser(Long id) {
        return employeeRepository.findOne(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteEmployee(Employee employee) {
        AuditLog log = new AuditLog(getAuthenticatedUser().getUsername(), "DELETED: "+ employee.getUsername() + " [" + employee.getUserRole() +"]", new Date());
        auditLogRepository.save(log);
        employeeRepository.delete(employee);

    }

    private UserData getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findFirstByUsername(auth.getName());
    }
}
