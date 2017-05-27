package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.referencePerson.Caregiver;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.services.CaregiverService;
import at.qe.sepm.asn_app.services.ChildService;

import at.qe.sepm.asn_app.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;



/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 14.04.17.
 */
@Component
@Scope("application")
public class ChildController {

    @Autowired
    private ChildService childService;
    @Autowired
    private ParentService parentService;
    @Autowired
    private CaregiverService caregiverService;
    private Child child;
    private Child childEdit;
    private Caregiver caregiver;
    private boolean care;
    private Collection<Child> children;

    private String parentUserName;

    public void setChildren(Collection<Child> children) {
        this.children = children;
    }

    public Collection<Child> getChildren(){
        return children;
    }

    public Collection<Child> getChildrenByParentUsername(String usrn){return childService.getChildrenByParentUsername(usrn);}
    /*
    public Collection<Child> getChildrenByParent(Parent parent){return childService.getChildrenByParent(parent);}
    */
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

    public Child getChildEdit() {
        return childEdit;
    }

    public void setChildEdit(Child childEdit) {
        this.childEdit = childEdit;
        doReloadChildEdit();
    }

    public void findParentByUsername(String usrn){
        child.setPrimaryParent(parentService.loadParent(usrn));
    }

    public String getParentUserName() {
        return parentUserName;
    }

    public void setParentUserName(String parentUserName) {
        this.parentUserName = parentUserName;
    }

    public void doSaveChild(){
        findParentByUsername(parentUserName);
        child = childService.saveChild(child);
        child = null;
        initNewChild();
        initList();
    }

    public void doSaveChildEdit() {
        childEdit = childService.saveChild(childEdit);
        initList();
    }

    public void doDeleteChild() {
        this.childService.deleteChild(childEdit);
        childEdit = null;
    }
    public void doReloadChild(){
        child = childService.loadChild(child.getId());
    }
    public void doReloadChildEdit(){
        childEdit = childService.loadChild(childEdit.getId());
    }

	public boolean getCare() {
		return care;
	}

	public void setCare(boolean care) {
		this.care = care;
	}

	public Caregiver getCaregiver() {
		return caregiver;
	}

	public void setCaregiver(Caregiver caregiver) {
		this.caregiver = caregiver;
	}


}
