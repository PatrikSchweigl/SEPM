package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.nursery.Registration;
import at.qe.sepm.asn_app.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 26.05.2017
 */
@Component
@Scope("view")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;


    @PostConstruct
    public void initList() {
        setRegistrations(registrationService.getAllRegistrations());
    }

    public Collection<Registration> getRegistrationsByDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd k:mm:ss z yyyy");
        Date d = new Date();
        try {
            d = formatter.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.DAY_OF_MONTH, 1);

            d = cal.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return registrationService.getAllRegistrationsByDate(d);
    }


    public Collection<Registration> getRegistrationsByDateToday() {
        return registrationService.getAllRegistrationsByDate(new Date());
    }

    public void setRegistrations(Collection<Registration> registrations) {
    }


}
