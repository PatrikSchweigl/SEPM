package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.nursery.NurseryInformation;
import at.qe.sepm.asn_app.services.NurseryInformationService;
import at.qe.sepm.asn_app.ui.constraints.NurseryConstraints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at> on
 * 15.05.2017
 */
@Component
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
		doReloadNurseryInformation();
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

	public void doSaveNurseryInformation() {
		if (nurseryConstraints.nurseryInfoExists(nurseryInformation)
				|| nurseryInformation.getOriginDate().compareTo(new Date()) <= 0
				|| nurseryConstraints.checkTimeConstraints(nurseryInformation) == false) {
			return;
		}
		nurseryInformation = nurseryInformationService.saveNurseryInformation(nurseryInformation);
		nurseryInformation = null;
		initNewNurseryInformation();
	}

	public void doDeleteNurseryInformation() {
		nurseryInformationService.deleteNurseryInformation(nurseryInformation);
		nurseryInformation = null;
	}

	public void doReloadNurseryInformation() {
		nurseryInformation = nurseryInformationService.loadNurseryInformation(nurseryInformation.getId());
	}
}
