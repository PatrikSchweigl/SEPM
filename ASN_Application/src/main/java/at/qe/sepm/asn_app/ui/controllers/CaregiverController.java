package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.referencePerson.Caregiver;
import at.qe.sepm.asn_app.services.CaregiverService;
import at.qe.sepm.asn_app.services.ChildService;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;


/**
 * Created by Auki on 24.04.2017.
 */

@Component
@Scope("view")
public class CaregiverController {

    @Autowired
    private CaregiverService caregiverService;
    @Autowired
    private ChildService childService;
    private Caregiver caregiver;
    private Caregiver caregiverEdit;
    private Long childId;

    private Collection<Caregiver> caregivers;

    public void setCaregivers(Collection<Caregiver> caregivers) {
        this.caregivers = caregivers;
    }

    public void setCaregiver(Caregiver caregiverEdit) {
        this.caregiver = caregiverEdit;
        doReloadCaregiver();
    }
    
    public Collection<Caregiver> getCaregiverByChildId(Long id){
    	return caregiverService.getAllCaregiversByChildId(id);
    }

    public void doReloadCaregiver() {
        caregiver = caregiverService.loadCaregiver(caregiver.getId());
    }

    @PostConstruct
    private void initNewCaregiver(){
        caregiver = new Caregiver();
    }

    @PostConstruct
    private void initList(){
        setCaregivers(caregiverService.getAllCaregivers());
    }

    public Caregiver getCaregiver() {
        return caregiver;
    }

    public Collection<Caregiver> getCaregivers(){
        return caregivers;
    }


    public void doSaveCaregiver(){
        caregiver = caregiverService.saveCaregiver(caregiver);
        String name = childService.addCaregiver(caregiver);
        caregiver.setChildname(name);
        caregiverService.saveCaregiver(caregiver);
        caregiver = null;
        initNewCaregiver();
    }




    public Caregiver getCaregiverEdit() {
        return caregiverEdit;
    }

    public void setCaregiverEdit(Caregiver caregiver) {
        this.caregiverEdit = caregiver;
        doReloadCaregiverEdit();
    }

    public void doReloadCaregiverEdit() {
        //child = childService.loadUser(child.getUsername());
        caregiverEdit = caregiverService.loadCaregiver(caregiverEdit.getId());
    }
    
    public void setEligibleToTrue(){
    	caregiverEdit.setEligible(true);
    	caregiverEdit = caregiverService.saveCaregiver(caregiverEdit);
    }

    public void doSaveCaregiverEdit(){
        caregiverEdit = caregiverService.saveCaregiver(caregiverEdit);
    }

    public void doDeleteCaregiverEdit() {
        this.caregiverService.deleteCaregiver(caregiverEdit);
        caregiverEdit = null;
    }

	public Long getChildId() {
		return childId;
	}

	public void setChildId(Long childId) {
		this.childId = childId;
	}


}
