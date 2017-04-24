package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.referencePerson.Caregiver;
import at.qe.sepm.asn_app.services.CaregiverService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Auki on 24.04.2017.
 */
public class CaregiverEditController {
    @Autowired
    private CaregiverService caregiverService;

    private Caregiver caregiver;

    public Caregiver getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(Caregiver caregiver) {
        this.caregiver = caregiver;
        doReloadCaregiver();
    }

    public void doReloadCaregiver() {
        //child = childService.loadUser(child.getUsername());
        caregiver = caregiverService.loadCaregiver(caregiver.getId());
    }

    public void doSaveCaregiver(){
        caregiver = caregiverService.saveCaregiver(caregiver);
    }

    public void doDeleteCaregiver() {
        this.caregiverService.deleteCaregiver(caregiver);
        caregiver = null;
    }
}
