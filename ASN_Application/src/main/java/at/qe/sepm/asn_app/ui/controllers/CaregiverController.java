package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.referencePerson.Caregiver;
import at.qe.sepm.asn_app.services.CaregiverService;
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

    private Caregiver caregiver;
    private Caregiver caregiverEdit;

    private Collection<Caregiver> caregivers;

    public void setCaregivers(Collection<Caregiver> caregivers) {
        this.caregivers = caregivers;
    }

    public void setCaregiver(Caregiver caregiverEdit) {
        this.caregiver = caregiverEdit;
        doReloadCaregiver();
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
        //System.out.println("Saving child: " + child.getFirstName() + " " + child.getLastName());
        caregiver = caregiverService.saveCaregiver(caregiver);
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

    public void doSaveCaregiverEdit(){
        caregiverEdit = caregiverService.saveCaregiver(caregiverEdit);
    }

    public void doDeleteCaregiverEdit() {
        this.caregiverService.deleteCaregiver(caregiverEdit);
        caregiverEdit = null;
    }


}
