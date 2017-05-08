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

    private Collection<Parent> parents;

    private Parent parent;

    public Collection<Parent> getParents() {

        return parents;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
        doReloadParent();
    }

    public void setParents(Collection<Parent> parents) {
        this.parents = parents;
    }

    @PostConstruct
    public void initNewParent() {
        parent = new Parent();
    }

    @PostConstruct
    public void initList(){
        setParents(parentService.getAllParents());
    }

    public void doSaveParent() {
        parent = parentService.saveParent(parent);
        parent = null;
        initNewParent();
        initList();
    }

    public Parent getParent() {
        return parent;
    }

    public void doReloadParent() {
        parent = parentService.loadParent(parent.getId());
    }

    private Parent parentEdit;

    public Parent getParentEdit() {
        return parentEdit;
    }

    public void setParentEdit(Parent parent) {
        this.parentEdit = parent;
        doReloadParentEdit();
    }

    public void doReloadParentEdit() {
        parentEdit = parentService.loadParent(parentEdit.getId());
    }

    public void doSaveParentEdit(){
        parentEdit = parentService.saveParent(parentEdit);
        initList();
    }

    public void doDeleteParentEdit() {
        this.parentService.deleteParent(parentEdit);
        parentEdit = null;
    }


}
