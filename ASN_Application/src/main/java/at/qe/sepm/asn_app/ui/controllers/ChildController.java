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

    public Child getChild() {
        return child;
    }

    public Collection<Child> getChilds(){
        return childService.getAllChilds();
    }

    public void doSaveChild(){
        //System.out.println("Saving child: " + child.getFirstName() + " " + child.getLastName());
        child = childService.saveChild(child);
        child = null;
        initNewChild();
    }


    /*
    public void parseDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");
        DateTime dateTime = dateTimeFormatter.parseDateTime(date);
        child.setBirthday(dateTime);
    }
    */
}
