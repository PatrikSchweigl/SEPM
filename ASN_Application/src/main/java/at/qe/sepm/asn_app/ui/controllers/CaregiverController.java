package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.referencePerson.Caregiver;
import at.qe.sepm.asn_app.services.CaregiverService;
import at.qe.sepm.asn_app.services.ChildService;

import at.qe.sepm.asn_app.services.MailService;
import at.qe.sepm.asn_app.ui.beans.SessionInfoBean;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Collection;


/**
 * Created by Auki on 24.04.2017.
 */

@Component
//@Scope("view")
@Scope("view")
public class CaregiverController {

    @Autowired
    private CaregiverService caregiverService;
    @Autowired
    private SessionInfoBean sessionInfoBean;
    @Autowired
    private MailService mailService;

    private Caregiver caregiver;
    private Caregiver caregiverEdit;

    private Collection<Caregiver> caregivers;
    private Collection<Caregiver> caregiversEmployee;

    public void setCaregivers(Collection<Caregiver> caregivers) {
        this.caregivers = caregivers;
    }

    public void setCaregiver(Caregiver caregiverEdit) {
        this.caregiver = caregiverEdit;
        doReloadCaregiver();
    }

    public Collection<Caregiver> getCaregiversEmployee() {
        return caregiversEmployee;
    }

    public void setCaregiversEmployee(Collection<Caregiver> caregiversEmployee) {
        this.caregiversEmployee = caregiversEmployee;
    }

    /**
     * Needed for JUnit tests.
     */
    public void setCaregiver2(Caregiver caregiverEdit) {
        this.caregiver = caregiverEdit;
    }

    public Collection<Caregiver> getAllCaregiversByEligibleFalse() {
        return caregiverService.getAllCaregiversByEligibleFalse();
    }

    public void doReloadCaregiver() {
        caregiver = caregiverService.loadCaregiver(caregiver.getId());
    }

    @PostConstruct
    private void initNewCaregiver() {
        caregiver = new Caregiver();
    }

    @PostConstruct
    public void initList() {
        setCaregiversEmployee(caregiverService.getAllCaregiversByEligibleTrue());
        setCaregivers(caregiverService.getCaregiversForParent(sessionInfoBean.getCurrentUserName()));
    }

    public Caregiver getCaregiver() {
        return caregiver;
    }

    public Collection<Caregiver> getCaregivers() {
        return caregivers;
    }


    public Caregiver doSaveCaregiver() {
        Caregiver caregiverReturn = null;
        if (!StringUtils.isNumeric(caregiver.getPhoneNumber())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Telefonnummer enthält Buchstaben!", null));
        } else {
            caregiver.setImgName("emptypicture.png");
            caregiver = caregiverService.saveCaregiver(caregiver);
            caregiverReturn = caregiver;
            initList();
            initNewCaregiver();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('caregiverAddDialog').hide()");
        }
        return caregiverReturn;
    }


    public Caregiver getCaregiverEdit() {
        return caregiverEdit;
    }

    public void setCaregiverEdit(Caregiver caregiver) {
        caregiverEdit = caregiver;
        doReloadCaregiverEdit();
    }

    /**
     * Needed for JUnit tests
     */
    public void setCaregiverEdit2(Caregiver caregiver) {
        this.caregiverEdit = caregiver;
    }

    public void doReloadCaregiverEdit() {
        //child = childService.loadUser(child.getUsername());
        caregiverEdit = caregiverService.loadCaregiver(caregiverEdit.getId());
    }

    public void setEligibleToTrue() {
        caregiverEdit.setEligible(true);
        caregiverEdit = caregiverService.saveCaregiver(caregiverEdit);

        mailService.sendEmail(caregiverEdit.getChild().getPrimaryParent().getEmail(), "Care4Fun - Bestätigung Bezugsperson",
                "Guten Tag " + caregiverEdit.getChild().getPrimaryParent().getFirstName() + " " + caregiverEdit.getChild().getPrimaryParent().getFirstName() + "!\n\n" +
                        "Soeben wurde die von Ihnen hinzugefügte Bezugsperson " + caregiverEdit.getFullName() + " als bestätigt markiert.\n\n" +
                        "Bitte kontrollieren Sie, ob im Bearbeitungsfenster für diese Person bei 'Bestätigt' JA eingetragen wurde.\n" +
                        "Sollten Probleme auftreten, bitte umgehend beim Administrator melden.\n\n" +
                        "Liebe Grüße wünscht das Kinderkrippen-Team!");
    }

    public void doSaveCaregiverEdit() {
        if (!StringUtils.isNumeric(caregiverEdit.getPhoneNumber())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Telefonnummer enthält Buchstaben!", null));
        } else {
            caregiverEdit = caregiverService.saveCaregiver(caregiverEdit);
            initList();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('caregiverEditDialog').hide()");
        }
    }

    public void doDeleteCaregiverEdit() {
        caregiverService.deleteCaregiver(caregiverEdit);
        caregiverEdit = null;
    }


}
