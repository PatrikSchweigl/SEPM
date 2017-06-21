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
public class RegistrationMonthController {

    @Autowired
    private RegistrationService registrationService;
    private Collection<Registration> monthlyRegistrations;

    public Collection<Registration> getMonthlyRegistrations() {
        return monthlyRegistrations;
    }

    public void setMonthlyRegistrations(Collection<Registration> monthlyRegistrations) {
        this.monthlyRegistrations = monthlyRegistrations;
    }

    public List<Registration> getRegistrationReportForMonth(int i) {
        Date start = new Date();
        start.setDate(1);
        start.setMonth((start.getMonth() + i) % 12);
        Date end = new Date();
        end.setDate(1);
        end.setMonth((end.getMonth() + i + 1) % 12);
        if (end.getMonth() == 0) {
            end.setYear(end.getYear() + 1);
        }
        return registrationService.getRegistrationInTimeWindowIE(start, end);
    }

    @PostConstruct
    public void initList() {
        setMonthlyRegistrations(getRegistrationReportForMonth(0));
    }
}
