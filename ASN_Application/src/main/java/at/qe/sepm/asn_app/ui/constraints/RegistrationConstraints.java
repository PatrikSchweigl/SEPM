package at.qe.sepm.asn_app.ui.constraints;

import at.qe.sepm.asn_app.models.nursery.NurseryInformation;
import at.qe.sepm.asn_app.models.nursery.Registration;
import at.qe.sepm.asn_app.services.NurseryInformationService;
import at.qe.sepm.asn_app.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

@Component
@Scope("application")
public class RegistrationConstraints {

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private NurseryInformationService nurseryService;

    public boolean registrationExists(Registration reg) {
        Collection<Registration> register = registrationService.getAllRegistrations();
        Iterator<Registration> iterator = register.iterator();
        while (iterator.hasNext()) {
            Registration r = iterator.next();
            Date date = r.getDate();
            if (date.compareTo(reg.getDate()) == 0 && reg.getChild().getId() == r.getChild().getId()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfNurseryExists(Registration reg) {
        Collection<NurseryInformation> nurse = nurseryService.getAllInformation();
        Iterator<NurseryInformation> iterator = nurse.iterator();
        while (iterator.hasNext()) {
            NurseryInformation n = iterator.next();
            Date date = n.getOriginDate();
            if (date.compareTo(reg.getDate()) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean checkTimeConstraints(Registration reg) {
        NurseryInformation nurse = nurseryService.nurseryInformationByOriginDate(reg.getDate());
        Date dateStart = nurse.getBringStart();
        Date dateEnd = nurse.getBringEnd();
        return dateStart.compareTo(reg.getBringdate()) > 0 || dateEnd.compareTo(reg.getBringdate()) < 0;
    }

    public boolean checkIfChildIsRegisteredOnDate(Date d, Long childId) {
        Collection<Registration> registrations = registrationService.getAllRegistrationsByDate(d);

        if (!registrations.isEmpty()) {
            for (Registration r : registrations) {
                if (r.getChild().getId() == childId) {
                    return true;
                }

            }
        }
        return false;
    }

}
