package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.employee.Employee;
import at.qe.sepm.asn_app.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 24.03.2017
 */

@Component
@Scope("view")
public class EmployeeEditController {
    @Autowired
    private EmployeeService employeeService;

    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        doReloadEmployee();
    }

    public void doReloadEmployee() {
        employee = employeeService.loadUser(employee.getUsername());
    }

    public void doSaveEmployee(){
        employee = employeeService.saveEmployee(employee);
    }

    public void doDeleteEmployee() {
        this.employeeService.deleteEmployee(employee);
        employee = null;
    }
}
