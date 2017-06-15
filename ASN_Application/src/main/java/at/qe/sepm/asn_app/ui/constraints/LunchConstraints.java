package at.qe.sepm.asn_app.ui.constraints;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import at.qe.sepm.asn_app.models.nursery.Lunch;
import at.qe.sepm.asn_app.models.nursery.NurseryInformation;
import at.qe.sepm.asn_app.services.LunchService;
import at.qe.sepm.asn_app.services.NurseryInformationService;

@Component
@Scope("application")
public class LunchConstraints {

	@Autowired
	private LunchService lunchService;
	@Autowired
	private NurseryInformationService nurseryService;


	/**
	 * Call all other lunch constraints from this method
	 * @param lunch the lunch of which the constraints are to be checked
	 * @return <code>true</code> iff no constraints are violated; <code>false</code> otherwise.
	 */
	public boolean checkLunchConstraints(Lunch lunch) {
		if (checkIfLunchExists(lunch)) {
			return false;
		}
		else if (checkIfNurseryInformationExists(lunch)) {
			return false;
		}
		else if (!checkTimeConstraints(lunch)) {
			return false;
		}
		return true;
	}

	public boolean checkIfLunchExists(Lunch lunch) {
		Collection<Lunch> l = lunchService.getLunchByDate(lunch.getDate());
		Iterator<Lunch> iterator = l.iterator();
		while (iterator.hasNext()) {
			Lunch temp = iterator.next();
			if (temp.getDate().compareTo(lunch.getDate()) == 0)
				return true;
		}
		return false;
	}

	public boolean checkIfNurseryInformationExists(Lunch lunch) {
		Collection<NurseryInformation> n = nurseryService.getAllInformation();
		Iterator<NurseryInformation> iterator = n.iterator();
		while (iterator.hasNext()) {
			NurseryInformation nursery = iterator.next();
			if (lunch.getDate().compareTo(nursery.getOriginDate()) == 0) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkTimeConstraints(Lunch lunch){
		Calendar fridayConstraint = Calendar.getInstance();
		fridayConstraint.setTime(new Date());
		fridayConstraint.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		fridayConstraint.set(Calendar.HOUR_OF_DAY, 12);
		fridayConstraint.set(Calendar.MINUTE, 0);
		Calendar cal = Calendar.getInstance();
		cal.setTime(lunch.getDate());
		System.err.println("JEEEEEEEEEEEEEEEEEEEEEEEETZT");
		System.err.println(fridayConstraint.getTime());
		System.err.println(cal.getTime());
		if(new Date().compareTo(fridayConstraint.getTime()) > 0)
			return false;
		System.err.println("JEEEEEEEEEEEEEEEEEEEEEEEETZT ABBBBER");
		System.err.println(cal.get(Calendar.WEEK_OF_YEAR) + " vergleichen mit " + fridayConstraint.get(Calendar.WEEK_OF_YEAR));
		if(cal.get(Calendar.WEEK_OF_YEAR) <= fridayConstraint.get(Calendar.WEEK_OF_YEAR)){
			return false;
		}
		return true;
			
	}

}
