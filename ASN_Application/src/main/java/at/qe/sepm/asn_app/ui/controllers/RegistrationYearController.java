package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.nursery.Registration;
import at.qe.sepm.asn_app.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.06.2017
 */
@Component
@Scope("view")
public class RegistrationYearController {
    @Autowired
    private RegistrationService registrationService;

    private Collection<Registration> yearlyRegistrations;

    public Collection<Registration> getYearlyRegistrations() {
        return yearlyRegistrations;
    }

    public void setYearlyRegistrations(Collection<Registration> yearlyRegistrations) {
        this.yearlyRegistrations = yearlyRegistrations;
    }

    public List<Registration> getRegistrationReportForYear(int i) {
        Date start = new Date();
        start.setDate(1);
        start.setMonth(0);
        start.setYear(start.getYear() + i);
        Date end = new Date();
        end.setDate(1);
        end.setMonth(0);
        end.setYear(end.getYear() + i + 1);
        return registrationService.getRegistrationInTimeWindowIE(start, end);
    }

    @PostConstruct
    public void initList() {
        setYearlyRegistrations(getRegistrationReportForYear(0));
    }

}
