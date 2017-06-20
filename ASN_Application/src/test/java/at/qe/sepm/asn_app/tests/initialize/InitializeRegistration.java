package at.qe.sepm.asn_app.tests.initialize;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.nursery.Registration;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 17.06.17 15:10 CEST.
 */
public class InitializeRegistration {

    public static Registration initialize() {
        Child child = InitializeChild.initialize();

        Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("Europe/Vienna"));
        calendar.clear();
        calendar.set(2017, Calendar.JULY, 14, 8, 0);
        Date bringDate = calendar.getTime();

        calendar.clear();
        calendar.set(2017, Calendar.JULY, 14, 2, 0);
        Date date = calendar.getTime();

        Registration registration = new Registration();
        registration.setBringdate(bringDate);
        registration.setChild(child);
        registration.setDate(date);
        registration.setNote("RegistrationNote1");

        return registration;
    }
}
