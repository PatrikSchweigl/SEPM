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
    private Employee employeeEdit;

    public Employee getEmployeeEdit() {
        return employeeEdit;
    }

    public void setUser(Employee employeeEdit) {
        this.employeeEdit = employeeEdit;
        doReloadUser();
    }

    public void doReloadUser() {
        employeeEdit = employeeService.loadUser(employeeEdit.getUsername());
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

    public Map<String,String> getFamilyStatus(){
        Map<String,String> familystatus = new HashMap<String,String>();

        familystatus.put("MARRIED","MARRIED");
        familystatus.put("NOT_MARRIED","NOT_MARRIED");
        familystatus.put("DIVORCED","DIVORCED");
        familystatus.put("WIDOWED","WIDOWED");
        familystatus.put("REGISTERED_PARTNERSHIP","REGISTERED_PARTNERSHIP");

        return familystatus;
    }

    public Map<String,String> getReligions(){
        Map<String,String> religions =  new HashMap<String, String>();

        religions.put("ISLAM", "ISLAM");
        religions.put("CHRISTIANITY","CHRISTIANITY");
        religions.put("JUDAISM","JUDAISM");
        religions.put("BUDDHISM","BUDDHISM");
        religions.put("HINDUISM","HINDUISM");
        religions.put("ATHEISM","ATHEISM");
        return religions;
    }

    public Map<String,String> getWorkRoles(){
        Map<String,String> roles =  new HashMap<String, String>();

        roles.put("PEDAGOGUE", "PEDAGOGUE");
        roles.put("TRAINEE","TRAINEE");
        return roles;
    }
}
