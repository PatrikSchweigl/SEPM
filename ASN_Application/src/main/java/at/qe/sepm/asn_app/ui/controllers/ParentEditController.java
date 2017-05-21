package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.services.MailService;
import at.qe.sepm.asn_app.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 15.05.2017
 */
@Component
@Scope("view")
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

    public void doReloadParent(){
        parent = parentService.loadParent(parent.getUsername());
    }

    public void doSaveParent(){
        parent = parentService.saveParent(parent);
        parent = null;
        parentController.initList();
    }

    public void doDeleteParent(){
        parentService.deleteParent(parent);
        parent = null;
        parentController.initList();
    }

    public void doResetPassword(){
        mailService.sendEmail(parent.getEmail(), "WICHTIG - Password geändert",
                "Guten Tag!\n\n" +
                        "Soeben wurde Ihr Passwort zurückgesetzt.\n\n" +
                        "Ihr Benutzername: "+ parent.getUsername() + "\n" +
                        "Ihr Passwort: passwd" +
                        "\n\nBitte ändern Sie nach dem ersten Login Ihr Password.\n" +
                        "Sollten Probleme auftauchen, bitte umgehend beim Administrator melden.\n\n" +
                        "Viel Spaß wünschen das Kinderkrippen Team!");
        parentService.resetPassword(parent);
    }

}
