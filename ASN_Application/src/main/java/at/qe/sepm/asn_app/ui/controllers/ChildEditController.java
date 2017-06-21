package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.nursery.Lunch;
import at.qe.sepm.asn_app.models.nursery.Registration;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.services.ChildService;
import at.qe.sepm.asn_app.services.LunchService;
import at.qe.sepm.asn_app.services.ParentService;
import at.qe.sepm.asn_app.services.RegistrationService;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionSystemException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 13.06.2017
 */
@Component
@Scope("view")
public class ChildEditController {

    @Autowired
    private ChildService childService;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private ChildController childController;
    @Autowired
    private CaregiverController caregiverController;
    @Autowired
    private ParentService parentService;
    @Autowired
    private LunchService lunchService;

    private Child childEdit;


    public Child getChildEdit() {
        return childEdit;
    }

    public void setChildEdit(Child childEdit) {
        this.childEdit = childEdit;
    }

    public Child doSaveChildParent() {
        Child childReturn;
        childReturn = doSaveChildEmployee();
        return childReturn;
    }

    public Child doSaveChildEmployee() {
        Child childReturn = null;
        if (!StringUtils.isNumeric(childEdit.getEmergencyNumber())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notfallkontaktnummer enthält Buchstaben!", null));
        } else {
            try {
                childEdit = childService.saveChild(childEdit);
                childReturn = childEdit;
                childEdit = null;
                childController.initList();
                caregiverController.initList();
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('childEditDialog').hide()");
            } catch (TransactionSystemException ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Es müssen alle Felder ausgefüllt werden!", null));
            }
        }
        return childReturn;
    }

    public void doDeleteChild() {
        Parent parent = childEdit.getPrimaryParent();
        parent.getChildren().remove(childEdit);
        Collection<Registration> registrations = registrationService.getAllRegistrationsByChild(childEdit.getId());
        for (Registration r : registrations) {
            registrationService.deleteRegistration(r);
        }

        Collection<Lunch> lunches = lunchService.findAll();

        for (Lunch l : lunches) {
            l.getChildrenIds().remove(childEdit.getId());
        }

        childService.deleteChild(childEdit);


        if (childService.getChildrenByParentUsername(parent.getUsername()).size() < 1) {
            parentService.changeStatus(parent, false);    // set parent status to inactive when last child is deleted
        }
        parentService.updateParent(parent, childEdit);
        childEdit = null;
        childController.initList();
    }

}
