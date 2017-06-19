package at.qe.sepm.c4f_app.ui.controllers;

import at.qe.sepm.c4f_app.models.nursery.NurseryInformation;
import at.qe.sepm.c4f_app.services.NurseryInformationService;
import at.qe.sepm.c4f_app.ui.constraints.NurseryConstraints;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionSystemException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at> on
 * 15.05.2017
 */
@Component
//@Scope("view")
@Scope("view")
public class NurseryInformationController {

	@Autowired
	private NurseryInformationService nurseryInformationService;
	private NurseryInformation nurseryInformation;
	@Autowired
	private NurseryConstraints nurseryConstraints;

	private Collection<NurseryInformation> nurseryInformations;

	public NurseryInformation getNurseryInformation() {
		return nurseryInformation;
	}

	public void setNurseryInformation(NurseryInformation nurseryInformation) {
		this.nurseryInformation = nurseryInformation;
	}

	public void setNurseryInformation2(NurseryInformation nurseryInformation) {
		this.nurseryInformation = nurseryInformation;
	}

	public Collection<NurseryInformation> getNurseryInformations() {
		return nurseryInformations;
	}

	public NurseryInformation getNurseryInformationByOriginDate(Date date) {
		return nurseryInformationService.nurseryInformationByOriginDate(date);
	}

	public void setNurseryInformations(Collection<NurseryInformation> nurseryInformations) {
		this.nurseryInformations = nurseryInformations;
	}

	@PostConstruct
	private void initNewNurseryInformation() {
		nurseryInformation = new NurseryInformation();
	}

	@PostConstruct
	private void initList() {
		setNurseryInformations(nurseryInformationService.getAllInformation());
	}

	public NurseryInformation doSaveNurseryInformation() {
		NurseryInformation nurseryInformationReturn = null;
		if (nurseryInformation.getMaxOccupancy() < 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Max. Belegung muss größer gleich 0 sein!", null));
		} else if(nurseryConstraints.nurseryInfoExists(nurseryInformation)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An diesem Tag existiert bereits ein Eintrag!", null));

		} else if(nurseryInformation.getOriginDate().compareTo(new Date()) <= 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datum liegt in der Vergangenheit!", null));
		} else if(nurseryConstraints.checkTimeConstraints(nurseryInformation) == false){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Von-Angaben müssen vor Bis-Angaben sein!", null));
		} else {
			try {
				nurseryInformation = nurseryInformationService.saveNurseryInformation(nurseryInformation);
				nurseryInformationReturn = nurseryInformation;
				nurseryInformation = null;
				initNewNurseryInformation();
				initList();
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('nurseryInformationDialog').hide()");
			}catch(TransactionSystemException ex){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Es müssen alle Felder ausgefüllt werden!", null));
			}
		}
		return nurseryInformationReturn;
	}

	public void doDeleteNurseryInformation() {
		nurseryInformationService.deleteNurseryInformation(nurseryInformation);
		nurseryInformation = null;
	}

	public void doReloadNurseryInformation() {
		nurseryInformation = nurseryInformationService.loadNurseryInformation(nurseryInformation.getId());
	}
}
