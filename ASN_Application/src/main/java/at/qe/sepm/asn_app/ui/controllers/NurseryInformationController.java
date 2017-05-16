package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.nursery.NurseryInformation;
import at.qe.sepm.asn_app.services.NurseryInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 15.05.2017
 */
@Component
@Scope("view")
public class NurseryInformationController {

    @Autowired
    private NurseryInformationService nurseryInformationService;
    private NurseryInformation nurseryInformation;

    private Collection<NurseryInformation> nurseryInformations;

    public NurseryInformation getNurseryInformation() {
        return nurseryInformation;
    }

    public void setNurseryInformation(NurseryInformation nurseryInformation) {
        this.nurseryInformation = nurseryInformation;
        doReloadNurseryInformation();
    }

    public Collection<NurseryInformation> getNurseryInformations() {
        return nurseryInformations;
    }

    public void setNurseryInformations(Collection<NurseryInformation> nurseryInformations) {
        this.nurseryInformations = nurseryInformations;
    }

    @PostConstruct
    private void initNewNurseryInformation(){
        nurseryInformation = new NurseryInformation();
    }

    @PostConstruct
    private void initList(){
        setNurseryInformations(nurseryInformationService.getAllInformation());
    }

    public void doSaveNurseryInformation(){
        nurseryInformation = nurseryInformationService.saveNurseryInformation(nurseryInformation);
        nurseryInformation = null;
        initNewNurseryInformation();
    }

    public void doDeleteNurseryInformation(){
        nurseryInformationService.deleteNurseryInformation(nurseryInformation);
        nurseryInformation = null;
    }

    public void doReloadNurseryInformation(){
        nurseryInformation = nurseryInformationService.loadNurseryInformation(nurseryInformation.getId());
    }
}
