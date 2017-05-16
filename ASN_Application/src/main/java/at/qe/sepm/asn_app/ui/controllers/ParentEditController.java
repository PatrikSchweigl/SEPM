package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.referencePerson.Parent;
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
        System.out.println("------------------------ Delete person "+ parent.getLastName());
        parentService.deleteParent(parent);
        parent = null;
        parentController.initList();
    }

}
