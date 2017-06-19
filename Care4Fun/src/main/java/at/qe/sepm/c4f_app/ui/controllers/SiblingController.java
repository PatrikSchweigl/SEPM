package at.qe.sepm.c4f_app.ui.controllers;

import at.qe.sepm.c4f_app.models.child.Sibling;
import at.qe.sepm.c4f_app.services.SiblingService;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionSystemException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Collection;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 13.06.2017
 */
@Component
@Scope("view")
public class SiblingController {
    @Autowired
    private SiblingService siblingService;
    private Sibling sibling;
    private Sibling siblingEdit;

    private Collection<Sibling> siblings;

    @PostConstruct
    private void initNewSibling() {
        sibling = new Sibling();
    }

    @PostConstruct
    private void initList() {
        setSiblings(siblingService.getAllSiblings());
    }

    public Sibling getSibling() {
        return sibling;
    }

    public void setSibling(Sibling sibling) {
        this.sibling = sibling;
    }

    public Sibling getSiblingEdit() {
        return siblingEdit;
    }

    public void setSiblingEdit(Sibling siblingEdit) {
        this.siblingEdit = siblingEdit;
    }

    public Collection<Sibling> getSiblings() {
        return siblings;
    }

    public void setSiblings(Collection<Sibling> siblings) {
        this.siblings = siblings;
    }

    public Sibling doSaveSibling() {
        Sibling siblingReturn = null;
        try {
            sibling = siblingService.saveSibling(sibling);
            sibling = null;
            initList();
            initNewSibling();
            siblingReturn = sibling;
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('siblingAddDialog').hide()");
        } catch (TransactionSystemException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Es müssen alle Felder ausgefüllt werden!", null));
        }

        return siblingReturn;
    }

    public Sibling doSaveSiblingEdit() {
        Sibling siblingReturn;
        siblingEdit = siblingService.saveSibling(siblingEdit);
        siblingReturn = siblingEdit;
        initList();
        return siblingReturn;
    }

    public void doDeleteSibling() {
        siblingService.deleteSibling(siblingEdit);
        siblingEdit = null;
    }
}
