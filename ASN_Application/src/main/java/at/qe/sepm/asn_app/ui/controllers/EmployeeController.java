package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.employee.Employee;
import at.qe.sepm.asn_app.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 24.03.2017
 */
@Component
@Scope("view")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private Employee employee;

    public void setUser(Employee employeeEdit) {
        this.employee = employeeEdit;
        doReloadUser();
    }

    public void doReloadUser() {
        employee = employeeService.loadUser(employee.getUsername());
    }

    @PostConstruct
    private void initNewEmployee(){
        employee = new Employee();
    }

    public Employee getEmployee() {
        return employee;
    }

    public Collection<Employee> getEmployees(){
        return employeeService.getAllEmployees();
    }

    public void doSaveEmployee(){
        employee = employeeService.saveEmployee(employee);
        employee = null;
        initNewEmployee();

    }

}
