package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.services.ChildService;
import at.qe.sepm.asn_app.services.MailService;
import at.qe.sepm.asn_app.services.ParentService;
import at.qe.sepm.asn_app.ui.constraints.UserConstraints;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.TransactionSystemException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Null;
import java.util.Collection;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 14.05.2017
 */
@Component
//@Scope("view")
@Scope("application")
public class ParentController {
    @Autowired
    private ParentService parentService;
    @Autowired
    private MailService mailService;
    @Autowired
    private ChildService childService;
    @Autowired
    private UserConstraints userConstraints;
    private String password;
    private Parent parent;
    private Collection<Parent> parents;


    public Collection<Parent> getParents() {
        return parents;
    }

    public void setParents(Collection<Parent> parents) {
        this.parents = parents;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Collection<Child> getChildren(String username) {
        return childService.getChildrenByParentUsername(username);
    }

    @PostConstruct
    public void initList() {
        setParents(parentService.getAllParents());
    }

    @PostConstruct
    private void initNewParent() {
        parent = new Parent();
    }

    public void doReloadParent() {
        parent = parentService.loadParent(parent.getUsername());
    }


    public void doSaveParent() {
        if (!StringUtils.isNumeric(parent.getPostcode())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Postleitzahl enthält Buchstaben!", null));
        } else if (!StringUtils.isNumeric(parent.getPhoneNumber())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Telefonnummer enthält Buchstaben oder Sonderzeichen (Leertaste, etc.)!", null));
        } else if (parent.getEmail().matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email Format ist nicht gültig!", null));
        } else if (userConstraints.checkIfUsernameExists(parent.getUsername())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Benutzername existiert bereits!", null));
        } else {
            try {
                parent = parentService.saveParent(parent);
                mailService.sendEmail(parent.getEmail(), "Care4Fun-App - Registrierung",
                        "Willkommen bei Care4Fun-Application!\n\n" +
                                "Die Plattform der Kinderkrippe erreichen Sie via localhost:8080.\n\n" +
                                "Ihr Benutzername: " + parent.getUsername() + "\n" +
                                "Ihr Passwort: passwd" +
                                "\n\nBitte ändern Sie nach dem ersten Login Ihr Password.\n" +
                                "Sollten Probleme auftauchen, bitte umgehend beim Administrator melden.\n\n" +
                                "Viel Spaß wünschen das Kinderkrippen-Team!");
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('parentAddDialog').hide()");
            } catch (TransactionSystemException ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Es müssen alle Felder ausgefüllt werden!", null));
            }
            parent = null;
            initNewParent();
            initList();

        }
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
        doReloadParent();
    }

    public void setParent2(Parent parent) {
        this.parent = parent;
    }

    public void doChangePassword(String password) {
        parentService.changePassword(password);
    }


}
