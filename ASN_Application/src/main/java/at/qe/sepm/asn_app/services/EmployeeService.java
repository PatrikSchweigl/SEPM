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
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        employee.setPassword( passwordEncoder.encode(employee.getPassword()));
        employee.setUserRole(UserRole.EMPLOYEE);
        System.out.println(employee.getPassword() + employee.getReligion() + employee.getBirthday()); //just to test if the program reaches this part
        return employeeRepository.save(employee);
    }
}
