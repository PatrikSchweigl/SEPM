package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.services.ParentService;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 24.03.2017
 */
@Component
@Scope("view")
public class ParentController {
    @Autowired
    private ParentService parentService;

    private Parent parent;

    public Collection<Parent> getParents() {

        return parentService.getAllParents();
    }

    public void setUser(Parent parent) {
        this.parent = parent;
        doReloadUser();
    }

    @PostConstruct
    public void initNewParent() {
        parent = new Parent();
    }

    public void doSaveParent() {
        parent = parentService.saveParent(parent);
        parent = null;
        initNewParent();
    }

    public Parent getParent() {
        return parent;
    }

    public void doReloadUser() {
        parent = parentService.loadUser(parent.getUsername());
    }


}
