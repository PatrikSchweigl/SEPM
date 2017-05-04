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

    /*
    public Collection<Caregiver> getCaregiver(Child child){
        return caregiverService.getAllChildren();
    }
    */

    public void doSaveCaregiver(){
        //System.out.println("Saving child: " + child.getFirstName() + " " + child.getLastName());
        caregiver = caregiverService.saveCaregiver(caregiver);
        caregiver = null;
        initNewCaregiver();
    }


    /*
    public void parseDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");
        DateTime dateTime = dateTimeFormatter.parseDateTime(date);
        child.setBirthday(dateTime);
    }
    */
}
