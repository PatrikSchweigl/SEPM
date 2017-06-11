package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.employee.Employee;
import at.qe.sepm.asn_app.repositories.UserRepository;
import at.qe.sepm.asn_app.services.EmployeeService;
import at.qe.sepm.asn_app.services.MailService;
import at.qe.sepm.asn_app.ui.constraints.UserConstraints;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionSystemException;

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
        if (!StringUtils.isNumeric(employee.getPostcode())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Postleitzahl enthält Buchstaben!", null));
        } else if (!StringUtils.isNumeric(employee.getPhoneNumber())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Telefonnummer enthält Buchstaben oder Sonderzeichen (Leertaste, etc.)!", null));
        } else if (!employee.getEmail().matches("^$|^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email Format ist nicht gültig!", null));
        } else if (userConstraints.checkIfUsernameExists(employee.getUsername())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Benutzername existiert bereits!", null));
        } else {
            try {
                employee = employeeService.saveEmployee(employee);
                mailService.sendEmail(employee.getEmail(), "Care4Fun-App - Registrierung",
                        "Willkommen bei Care4Fun-Application!\n\n" +
                                "Die Plattform der Kinderkrippe erreichen Sie via localhost:8080.\n\n" +
                                "Ihr Benutzername: " + employee.getUsername() + "\n" +
                                "Ihr Passwort: passwd" +
                                "\n\nBitte ändern Sie nach dem ersten Login Ihr Password.\n" +
                                "Sollten Probleme auftauchen, bitte umgehend beim Administrator melden.\n\n" +
                                "Viel Spaß wünschen das Kinderkrippen-Team!");
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('employeeAddDialog').hide()");
            } catch (TransactionSystemException ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Es müssen alle Felder ausgefüllt werden!", null));
            }
            employee = null;
            initNewEmployee();
            initList();
        }
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
        if (!StringUtils.isNumeric(employeeEdit.getPostcode())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Postleitzahl enthält Buchstaben!", null));
        } else if (!StringUtils.isNumeric(employeeEdit.getPhoneNumber())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Telefonnummer enthält Buchstaben oder Sonderzeichen (Leertaste, etc.)!", null));
        } else if (!employeeEdit.getEmail().matches("^$|^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email Format ist nicht gültig!", null));
        } else{
                try {
                    System.err.println(employeeEdit.toString());
                    employeeEdit = employeeService.saveEmployee(employeeEdit);
                    mailService.sendEmail(employeeEdit.getEmail(), "Care4Fun-App - Änderung Benutzerdaten",
                            "Guten Tag " + employeeEdit.getFirstName() + " " + employeeEdit.getLastName() + "!\n\n" +
                                    "Soeben wurden Ihre Benutzerdaten geändert.\n\n" +
                                    "Bitte kontrollieren Sie Ihre Daten unter dem Menüpunkt Eigene Daten.\n" +
                                    "Sollten Probleme auftauchen, bitte umgehend beim Administrator melden.\n\n" +
                                    "Viel Spaß wünschen das Kinderkrippen-Team!");
                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("PF('employeeEditDialog').hide()");
                } catch (TransactionSystemException ex) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Es müssen alle Felder ausgefüllt werden!", null));
                }
                employeeEdit = null;
                initNewEmployee();
                initList();
        }
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
