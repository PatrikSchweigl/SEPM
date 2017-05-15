package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 14.05.2017
 */
@Component
@Scope("view")
public class ParentController {
    @Autowired
    private ParentService parentService;

    private Parent parent;


    private Collection<Parent> parents;

    public Collection<Parent> getParents(){
        return parents;
    }
    public void setParents(Collection<Parent> parents) {
        this.parents = parents;
    }

    @PostConstruct
    public void initList(){
        setParents(parentService.getAllParents());
    }

    @PostConstruct
    private void initNewParent(){
        parent = new Parent();
    }

    public void doReloadParent(){
        parent = parentService.loadParent(parent.getUsername());
    }


    public void doSaveParent(){
        parent = parentService.saveParent(parent);
        parent = null;
        initNewParent();
        initList();
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
        doReloadParent();
    }





}
