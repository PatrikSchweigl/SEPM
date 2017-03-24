package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.employee.Employee;
import at.qe.sepm.asn_app.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by stefa on 24.03.2017.
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

    public void setEmployee(Employee employeeEdit) {
        this.employee = employeeEdit;
        doReloadUser();
    }

    public void doReloadUser() {
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
