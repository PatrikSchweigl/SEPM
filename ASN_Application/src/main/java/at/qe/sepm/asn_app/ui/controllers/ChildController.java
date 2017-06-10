package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.nursery.Lunch;
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
import java.util.HashSet;
import java.util.Set;


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
	private ParentController parentController;
	@Autowired
	private CaregiverService caregiverService;
	private Child child;
	private Child childEdit;
	private Caregiver caregiver;
	private String allergy;
	private boolean detail;
	private String intolerance;
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

	public Collection<Child> getChildrenByLunch(Lunch lunch){
		Set<Child> ret = new HashSet<Child>();
		for(Long id : lunch.getChildrenIds()){
			ret.add(childService.loadChild(id));
		}
		return ret;
	}
	@PostConstruct
	public void initList(){
		children = childService.getAllChildren();
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

    /**
     * Needed for JUnit tests
     * @param child The child to be saved in the database.
     */
    public void setChild2(Child child) {
        this.child = child;
    }

    public Child getChildEdit() {
        return childEdit;
    }

	public void setChildEdit(Child childEdit) {
		this.childEdit = childEdit;
		doReloadChildEdit();
	}

    /**
     * Needed for JUnit tests
     * @param childEdit The child to be saved in the database.
     */
    public void setChildEdit2(Child childEdit) {
        this.childEdit = childEdit;
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

	public Child doSaveChild(){
		findParentByUsername(parentUserName);
		Parent parent = parentService.loadParent(parentUserName);
		parentService.changeStatus(parent, true);	// set parent status to active when child is added
		Child childReturn = childService.saveChild(child);
		child = null;
		initNewChild();
		initList();
		parentController.initList();
		return childReturn;
	}

	public void doSaveChildEdit() {
		if(allergy.compareTo("") != 0)
			childEdit.addAllergy(allergy);
		if(intolerance.compareTo("") != 0)
			childEdit.addFoodIntolerance(intolerance);
		childEdit = childService.saveChild(childEdit);
		initList();
	}

	public void doDeleteChild() {
		Parent parent = childEdit.getPrimaryParent();
		if(childService.getChildrenByParentUsername(parent.getUsername()).size() <= 1){
			parentService.changeStatus(parent, false);	// set parent status to inactive when last child is deleted
		}
		this.childService.deleteChild(childEdit);
		childEdit = null;
		children = childService.getAllChildren();
		parentController.initList();
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

	public String getAllergy() {
		return allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}

	public String getIntolerance() {
		return intolerance;
	}

	public void setIntolerance(String intolerance) {
		this.intolerance = intolerance;
	}

	public boolean isDetail() {
		return detail;
	}

	public void setDetail(boolean detail) {
		this.detail = detail;
	}


}
