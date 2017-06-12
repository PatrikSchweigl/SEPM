package at.qe.sepm.asn_app.ui.constraints;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import at.qe.sepm.asn_app.models.nursery.NurseryInformation;
import at.qe.sepm.asn_app.models.nursery.Registration;
import at.qe.sepm.asn_app.services.NurseryInformationService;
import at.qe.sepm.asn_app.services.RegistrationService;

@Component
@Scope("application")
public class RegistrationConstraints {

	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private NurseryInformationService nurseryService;

	public boolean registationExists(Registration reg) {
		Collection<Registration> register = registrationService.getAllRegistrations();
		Iterator<Registration> iterator = register.iterator();
		while (iterator.hasNext()) {
			Registration r = iterator.next();
			Date date = r.getDate();
			System.err.println(date + "  vergleich mit  " + reg.getDate());
			if (date.compareTo(reg.getDate()) == 0 && reg.getChild().getId() == r.getChild().getId()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkIfNurseryExists(Registration reg){
		Collection<NurseryInformation> nurse = nurseryService.getAllInformation();
		Iterator<NurseryInformation> iterator = nurse.iterator();
		while (iterator.hasNext()) {
			NurseryInformation n = iterator.next();
			Date date = n.getOriginDate();
			System.err.println(date + "  vergleich mit  " + reg.getDate());
			if (date.compareTo(reg.getDate()) == 0) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkTimeConstraints(Registration reg){
		NurseryInformation nurse = nurseryService.nurseryInformationByOriginDate(reg.getDate());
			Date dateStart = nurse.getBringStart();
			Date dateEnd = nurse.getBringEnd();
			System.err.println(dateStart + "  " +  dateEnd +  "  vergleich mit!!!!!!!!!!!!!  " + reg.getDate());
			if (dateStart.compareTo(reg.getBringdate()) > 0 || dateEnd.compareTo(reg.getBringdate()) < 0) {
				return true;
			}
		return false;
	}
}
