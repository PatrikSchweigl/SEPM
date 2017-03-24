package at.qe.sepm.asn_app.services;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.employee.Employee;
import at.qe.sepm.asn_app.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Autowired EmployeeRepository employeeRepository;


    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    public Collection<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Employee saveEmployee(Employee employee) {
        if(employee.isNew()){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            employee.setUserRole(UserRole.EMPLOYEE);
        }

        return employeeRepository.save(employee);
    }

    @PreAuthorize("hasAuthority('ADMIN') or principal.username eq #username")
    public Employee loadUser(String username) {
        return employeeRepository.findFirstByUsername(username);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }
}
