package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.employee.Employee;
import at.qe.sepm.asn_app.repositories.UserRepository;
import at.qe.sepm.asn_app.services.EmployeeService;
import at.qe.sepm.asn_app.services.MailService;
import at.qe.sepm.asn_app.ui.constraints.UserConstraints;
import com.google.gson.Gson;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 24.03.2017
 */
@Component
//@Scope("view")
@Scope("application")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private MailService mailService;
    private Collection<Employee> employees;
    private Employee employee;
    private Employee employeeEdit;
    private String password;
    @Autowired
	private UserRepository userRepository;
    @Autowired
    private UserConstraints userConstraints;



    public void setEmployee(Employee employee) {
        this.employee = employee;
        doReloadEmployee();
    }

    /**
     * Needed for JUNit tests
     * @param employee
     */
    public void setEmployee2(Employee employee) {
        this.employee = employee;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }
    public void doReloadEmployee() {
        employee = employeeService.loadEmployee(employee.getUsername());
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
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('employeeAddDialog').hide()");

    }

    public Employee getEmployeeEdit() {
        return employeeEdit;
    }

    public void setEmployeeEdit(Employee employeeEdit) {
        this.employeeEdit = employeeEdit;
        doReloadEmployeeEdit();
    }

    /**
     * Needed for JUnit tests
     * @param employeeEdit
     */
    public void setEmployeeEdit2(Employee employeeEdit) {
        this.employeeEdit = employeeEdit;
    }
    
    public void doChangePassword(String password){
    	employeeService.changePassword(password);
    }

	public UserData getAuthenticatedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		return userRepository.findFirstByUsername(auth.getName());
	}


    public void doReloadEmployeeEdit() {
        employeeEdit = employeeService.loadEmployee(employeeEdit.getUsername());
    }

    public void doSaveEmployeeEdit(){
        employeeEdit = employeeService.saveEmployee(employeeEdit);
        initList();
    }

    public void doDeleteEmployee() {
        employeeService.deleteEmployee(employeeEdit);
        employeeEdit = null;
        initList();
    }

    public void doResetPassword(){
        employeeService.resetPassword(employeeEdit);
    }
    
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return password;
	}

}
