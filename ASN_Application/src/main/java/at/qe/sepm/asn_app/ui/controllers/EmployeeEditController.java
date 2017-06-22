package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.employee.Employee;
import at.qe.sepm.asn_app.services.EmployeeService;
import at.qe.sepm.asn_app.services.MailService;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionSystemException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by Emanuel Striednig <emanuel.striednig@student.uibk.ac.at>
 * on 12.06.2017.
 */
@Component
@Scope("view")
public class EmployeeEditController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private MailService mailService;
    @Autowired
    private EmployeeController employeeController;

    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        doReloadEmployee();
    }



    public void doReloadEmployee() {
        employee = employeeService.loadEmployee(employee.getUsername());
    }

    public void doSaveEmployeeEdit() {
        if (!StringUtils.isNumeric(employee.getPostcode())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Postleitzahl enthält Buchstaben!", null));
        } else if (!StringUtils.isNumeric(employee.getPhoneNumber())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Telefonnummer enthält Buchstaben oder Sonderzeichen (Leertaste, etc.)!", null));
        } else if (!employee.getEmail().matches("^$|^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email Format ist nicht gültig!", null));
        } else {
            try {

                employee = employeeService.saveEmployee(employee);
                mailService.sendEmail(employee.getEmail(), "Care4Fun-App - Änderung Benutzerdaten",
                        "Guten Tag " + employee.getFirstName() + " " + employee.getLastName() + "!\n\n" +
                                "Soeben wurden Ihre Benutzerdaten geändert.\n\n" +
                                "Bitte kontrollieren Sie Ihre Daten unter dem Menüpunkt Eigene Daten.\n" +
                                "Sollten Probleme auftreten, bitte umgehend beim Administrator melden.\n\n" +
                                "Viel Spaß wünscht das Kinderkrippen-Team!");
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('employeeEditDialog').hide()");
            } catch (TransactionSystemException ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Es müssen alle Felder ausgefüllt werden!", null));
            }

            employeeController.initList();
            employee = null;
        }
    }

    public void doDeleteEmployee() {
        employeeService.deleteEmployee(employee);
        employee = null;
        employeeController.initList();
    }

    public void doResetPassword() {
        employeeService.resetPassword(employee);
    }

}

