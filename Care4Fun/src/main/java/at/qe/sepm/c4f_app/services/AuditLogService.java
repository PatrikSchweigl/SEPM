package at.qe.sepm.c4f_app.services;
import at.qe.sepm.c4f_app.models.nursery.AuditLog;
import at.qe.sepm.c4f_app.repositories.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.03.2017
 */
@Component
@Scope("application")
public class AuditLogService {
    @Autowired
    private AuditLogRepository auditLogRepository;

    public AuditLog saveAuditLog(AuditLog log){
        return auditLogRepository.save(log);
    }

    public AuditLog loadAuditLog(Long id) {
        return auditLogRepository.findOne(id);
    }
    //method to delete auditLog
    public void deleteAuditLog(AuditLog auditLog) {
        auditLogRepository.delete(auditLog);
    }
}