package at.qe.sepm.c4f_app.ui.controllers;

import at.qe.sepm.c4f_app.models.child.Child;
import at.qe.sepm.c4f_app.models.nursery.Lunch;
import at.qe.sepm.c4f_app.models.referencePerson.Caregiver;
import at.qe.sepm.c4f_app.models.referencePerson.Parent;
import at.qe.sepm.c4f_app.services.CaregiverService;
import at.qe.sepm.c4f_app.services.ChildService;

import at.qe.sepm.c4f_app.services.LunchService;
import at.qe.sepm.c4f_app.services.ParentService;
import at.qe.sepm.c4f_app.ui.beans.SessionInfoBean;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.TransactionSystemException;


/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 14.04.17.
 */
@Component
@Scope("request")
public class ChildController {

	@Autowired
	private ChildService childService;
	@Autowired
	private SessionInfoBean sessionInfo;
	@Autowired
	private ParentService parentService;
	@Autowired
	private ParentController parentController;
	@Autowired
	private CaregiverService caregiverService;
    @Autowired
    private LunchService lunchService;
	private Child child;
	private Caregiver caregiver;

	private Collection<Child> children;
	private Collection<Child> childrenParent;

	private String parentUserName;

	public void setChildren(Collection<Child> children) {
		this.children = children;
	}

	public Child findOne(Long id){
		return childService.loadChild(id);
	}

	public Collection<Child> getChildren(){
		return children;
	}

	public Collection<Child> getChildrenByParentUsername(String usrn){return childService.getChildrenByParentUsername(usrn);}
    /*
    public Collection<Child> getChildrenByParent(Parent parent){return childService.getChildrenByParent(parent);}
    */

    public Collection<Child> getChildrenByLunchToday(){
        Date today = new Date();
        List<Lunch> lunchs = lunchService.getLunchByDate(today);
        if(lunchs.size() < 1){
            return null;
        }
        Lunch lunch = lunchs.get(0);
        return getChildrenByLunch(lunch);
    }
	public Collection<Child> getChildrenByLunch(Lunch lunch){
		return childService.getChildrenByLunch(lunch);
	}


	@PostConstruct
	public void initList(){
		children = childService.getAllChildren();
		childrenParent = childService.getChildrenByParentUsername(sessionInfo.getCurrentUserName());
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
		Child childReturn = null; 	// Needed for JUnit tests

		if(!StringUtils.isNumeric(child.getEmergencyNumber())){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notfallkontaktnummer enthält Buchstaben!", null));
		}else{
			try{
				findParentByUsername(parentUserName);
				Parent parent = parentService.loadParent(parentUserName);
				parentService.changeStatus(parent, true);	// set parent status to active when child is added
				child = childService.saveChild(child);
				childReturn = child;
				child = null;
				initNewChild();
				initList();
				parentController.initList();
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('childAddDialog').hide()");
			} catch(TransactionSystemException ex){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Es müssen alle Felder ausgefüllt werden!", null));
			}
		}
		return childReturn;
	}

	public void doReloadChild(){
		child = childService.loadChild(child.getId());
	}

	public Collection<Child> getChildrenParent() {
		return childrenParent;
	}

	public void setChildrenParent(Collection<Child> childrenParent) {
		this.childrenParent = childrenParent;
	}

}
