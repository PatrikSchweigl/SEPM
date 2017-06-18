package at.qe.sepm.asn_app.tests.initialize;

import at.qe.sepm.asn_app.models.nursery.AuditLog;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 18.06.17 15:19 CEST.
 */
public class InitializeAuditLog {

    public static AuditLog initialize1() {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.clear();
        calendar.set(2017, Calendar.JULY, 11, 16, 47);

        AuditLog auditLog = new AuditLog();
        auditLog.setDate(calendar.getTime());
        auditLog.setLog("Log1");
        auditLog.setUserName("AuditLogUsername1");

        return auditLog;
    }
}
