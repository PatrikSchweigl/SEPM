package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.services.MailService;
import at.qe.sepm.asn_app.services.ParentService;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionSystemException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 15.05.2017
 */
@Component
//@Scope("view")
@Scope("application")
public class ParentEditController {

    @Autowired
    private ParentService parentService;
    @Autowired
    private ParentController parentController;
    @Autowired
    private MailService mailService;

    private Parent parent;

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

    public void doReloadParent(){
        parent = parentService.loadParent(parent.getUsername());
    }

    public void doSaveParent() {
        if (!StringUtils.isNumeric(parent.getPostcode())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Postleitzahl enthält Buchstaben!", null));
        } else if (!StringUtils.isNumeric(parent.getPhoneNumber())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Telefonnummer enthält Buchstaben oder Sonderzeichen (Leertaste, etc.)!", null));
        } else if (parent.getEmail().matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email Format ist nicht gültig!", null));
        } else {
            try {
                parent = parentService.saveParent(parent);
                mailService.sendEmail(parent.getEmail(), "Care4Fun-App - Änderung Benutzerdaten",
                        "Guten Tag " + parent.getFirstName() + " " + parent.getLastName() + "!\n\n" +
                                "Soeben wurden Ihre Benutzerdaten geändert.\n\n" +
                                "Bitte kontrollieren Sie Ihre Daten unter dem Menüpunkt Eigene Daten.\n" +
                                "Sollten Probleme auftauchen, bitte umgehend beim Administrator melden.\n\n" +
                                "Viel Spaß wünschen das Kinderkrippen-Team!");
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('parentAddDialog').hide()");
            } catch (TransactionSystemException ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Es müssen alle Felder ausgefüllt werden!", null));
            }
            parent = parentService.saveParent(parent);
            parent = null;
            parentController.initList();
        }
    }

    public void doDeleteParent(){
        parentService.deleteParent(parent);
        parent = null;
        parentController.initList();
    }

    public void doResetPassword(){
        mailService.sendEmail(parent.getEmail(), "Care4Fun - Password wurde geändert",
                "Guten Tag " + parent.getFirstName() + " " + parent.getLastName() + "\n\n" +
                        "Soeben wurde Ihr Passwort zurückgesetzt.\n\n" +
                        "Ihr Benutzername: "+ parent.getUsername() + "\n" +
                        "Ihr Passwort: passwd" +
                        "\n\nBitte ändern Sie nach dem ersten Login Ihr Password.\n" +
                        "Sollten Probleme auftauchen, bitte umgehend beim Administrator melden.\n\n" +
                        "Viel Spaß wünschen das Kinderkrippen-Team!");
        parentService.resetPassword(parent);
    }

}
