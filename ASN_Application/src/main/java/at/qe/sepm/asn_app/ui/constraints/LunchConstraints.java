package at.qe.sepm.asn_app.ui.constraints;

import java.util.Collection;
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

	public boolean checkIfLunchExists(Lunch lunch) {

		Collection<Lunch> l = lunchService.getLunchByDate(lunch.getDate());
		Iterator<Lunch> iterator = l.iterator();
		while (iterator.hasNext()) {
			Lunch temp = iterator.next();
			System.out.println(temp.getDate() + "  vergleich mit easymmmmmeasy  " + lunch.getDate());
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
			System.err.println("What WHATTTTTTTTTTT");
			System.out.println(lunch.getDate() + "  vergleich mit easymmmmmeasy  " + nursery.getOriginDate());
			if (lunch.getDate().compareTo(nursery.getOriginDate()) == 0) {
				System.err.println("What WHATTTTTTTTTTT");
				System.out.println("ICH BIN HIER UND TRINKE EINEN");

				return true;
			}
		}
		System.out.println("ODER DOCHNICHT");

		return false;
	}

}
