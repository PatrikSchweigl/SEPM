package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.employee.Employee;
import at.qe.sepm.asn_app.repositories.UserRepository;
import at.qe.sepm.asn_app.services.EmployeeService;
import at.qe.sepm.asn_app.services.MailService;
import at.qe.sepm.asn_app.services.UserService;
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
@Scope("view")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    private Collection<Employee> employees;
    private Employee employee;
    private String password;
    @Autowired
    private UserConstraints userConstraints;


    public void setEmployee(Employee employee) {
        this.employee = employee;
        doReloadEmployee();
    }

    /**
     * Needed for JUNit tests
     *
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
    private void initNewEmployee() {
        employee = new Employee();
    }

    @PostConstruct
    public void initList() {
        setEmployees(employeeService.getAllEmployees());
    }

    public Employee getEmployee() {
        return employee;
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void doChangePassword(String password) {
        employeeService.changePassword(password);
    }

    public void doSaveEmployee() {
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
                employee.setImgName("emptypicture.png");
                employee = employeeService.saveEmployee(employee);
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}
