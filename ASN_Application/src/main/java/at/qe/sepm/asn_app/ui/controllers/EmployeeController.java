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
    private Collection<Employee> employees;
    private Employee employee;
    private Employee employeeEdit;



    public void setEmployee(Employee employeeEdit) {
        this.employee = employeeEdit;
        doReloadEmployee();
    }
    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }
    public void doReloadEmployee() {
        employee = employeeService.loadEmployee(employee.getId());
    }

    @PostConstruct
    private void initNewEmployee(){
        employee = new Employee();
    }

    @PostConstruct
    private void initList(){
        setEmployees(employeeService.getAllEmployees());
    }

    public Employee getEmployee() {
        return employee;
    }

    public Collection<Employee> getEmployees(){
        return employees;
    }

    public void doSaveEmployee(){
        employee = employeeService.saveEmployee(employee);
        employee = null;
        initNewEmployee();
        initList();
    }

    public Employee getEmployeeEdit() {
        return employeeEdit;
    }

    public void setEmployeeEdit(Employee employee) {
        this.employeeEdit = employee;
        doReloadEmployeeEdit();
    }


    public void doReloadEmployeeEdit() {
        employeeEdit = employeeService.loadEmployee(employeeEdit.getId());
    }

    public void doSaveEmployeeEdit(){
        employeeEdit = employeeService.saveEmployee(employeeEdit);
        initList();
    }

    public void doDeleteEmployee() {
        System.out.println(employeeEdit.getFirstName());
        employeeService.deleteEmployee(employeeEdit);
        employeeEdit = null;
        initList();
    }

}
