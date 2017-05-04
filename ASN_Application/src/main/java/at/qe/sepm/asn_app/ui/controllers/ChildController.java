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

    public void setUser(Child childEdit) {
        this.child = childEdit;
        doReloadUser();
    }

    public void doReloadUser() {
        child = childService.loadUser(null);
    }

    @PostConstruct
    private void initNewChild(){
        child = new Child();
    }

    @PostConstruct
    private void initList(){
        setChildren(childService.getAllChildren());
    }

    public Child getChild() {
        return child;
    }

    public Collection<Child> getChildren(){
        return children;
    }

    public void doSaveChild(){
        //System.out.println("Saving child: " + child.getFirstName() + " " + child.getLastName());
        child = childService.saveChild(child);
        child = null;
        initNewChild();
    }


    public void parseBirthday(String birthday) {
        System.out.println("Test successful");
    }


    /*
    public void parseDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");
        DateTime dateTime = dateTimeFormatter.parseDateTime(date);
        child.setBirthday(dateTime);
    }
    */
}
