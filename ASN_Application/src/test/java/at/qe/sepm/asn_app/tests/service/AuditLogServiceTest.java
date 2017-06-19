package at.qe.sepm.asn_app.tests.service;

import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.services.AuditLogService;
import at.qe.sepm.asn_app.tests.initialize.InitializeAuditLog;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 18.06.17 15:18 CEST.
 */
@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuditLogServiceTest {

    @Autowired
    private AuditLogService auditLogService;
    private AuditLog auditLog;


    @Before
    public void initialize() {
        auditLog = InitializeAuditLog.initialize1();
    }


    @Test
    public void testSaveAndDelete() {
        // Save the AuditLog in the database.
        auditLog = auditLogService.saveAuditLog(auditLog);

        // Check if the values have changed since the auditLog was saved.
        AuditLog other = auditLogService.loadAuditLog(auditLog.getId());
        assertTrue(auditLog.equals(other));

        // Delete the auditLog again.
        auditLogService.deleteAuditLog(auditLog);
        other = auditLogService.loadAuditLog(auditLog.getId());
        assertFalse(auditLog.equals(other));
        assertNull(other);
    }


    @Test
    public void testSetterGetter() {
        // Initialize attributes
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.clear();
        calendar.set(2017, Calendar.JULY, 11, 16, 47);

        Date date = calendar.getTime();
        String log = "Log";
        String username = "AuditLogUsername";

        // Set attributes
        auditLog = new AuditLog();
        auditLog.setDate(date);
        auditLog.setLog(log);
        auditLog.setUserName(username);

        // Test getter
        assertEquals(date, auditLog.getDate());
        assertEquals(log, auditLog.getLog());
        assertEquals(username, auditLog.getUserName());
    }


    @Test
    public void testFurtherMethods() {
        // Test toString()
        assertNotNull(auditLog.toString());
        assertNotEquals("", auditLog.toString());

        // Test getFormattedDate()
        assertNotEquals("", auditLog.getFormattedOriginDate());

        // Test isNew()
        assertFalse(auditLog.isNew());
        auditLog = new AuditLog();
        assertTrue(auditLog.isNew());
    }


    @After
    public void cleanUp() {
        auditLog = null;
    }
}
