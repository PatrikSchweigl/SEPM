package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.services.ChildService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;


/**
 * Created by root on 14.04.17.
 */
@Component
@Scope("view")
public class ChildController {

    @Autowired
    private ChildService childService;

    private Child child;
    private Collection<Child> children;

    public void setChildren(Collection<Child> children) {
        this.children = children;
    }

    public Collection<Child> getChildren(){
        return children;
    }

    @PostConstruct
    public void initList(){
        setChildren(childService.getAllChildren());
    }

    @PostConstruct
    public void initNewChild(){
        child = new Child();
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
        doReloadChild();
    }

    public void doSaveChild(){


        System.out.println("HHHHHHHHHHHHHHEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEREEEEEEEEEEEEEEEEEEEEEEEE333333");

        childService.saveChild(child);
        child = null;
        initNewChild();
    }

    public void doReloadChild(){
        child = childService.loadChild(child.getId());
    }
}
