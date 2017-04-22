package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.services.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by root on 19.04.17.
 */
@Component
@Scope("view")
public class ChildEditController {
    @Autowired
    private ChildService childService;

    private Child child;

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
        doReloadChild();
    }

    public void doReloadChild() {
        //child = childService.loadUser(child.getUsername());
        child = childService.loadUser(null);
    }

    public void doSaveChild(){
        child = childService.saveChild(child);
    }

    public void doDeleteChild() {
        this.childService.deleteChild(child);
        child = null;
    }
}
