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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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


    @After
    public void cleanUp() {
        auditLog = null;
    }
}
