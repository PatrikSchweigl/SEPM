package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.nursery.Registration;
import at.qe.sepm.asn_app.services.RegistrationService;
import at.qe.sepm.asn_app.utils.DateUtils;
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
public class RegistrationWeekController {
    @Autowired
    private RegistrationService registrationService;

    private Collection<Registration> weeklyRegistrations;

    public Collection<Registration> getWeeklyRegistrations() {
        return weeklyRegistrations;
    }

    public void setWeeklyRegistrations(Collection<Registration> weeklyRegistrations) {
        this.weeklyRegistrations = weeklyRegistrations;
    }

    public List<Registration> getRegistrationReportForWeek(int i) {
        Date[] dates = DateUtils.getWeek(i);
        if (dates.length < 1) {
            return null;
        }
        Date start = dates[0];
        Date end = dates[dates.length - 1];
        return registrationService.getRegistrationInTimeWindowII(start, end);

    }

    @PostConstruct
    public void initList() {
        setWeeklyRegistrations(getRegistrationReportForWeek(0));
    }
}

