package at.qe.sepm.asn_app.services;
import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.repositories.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sun.reflect.generics.repository.AbstractRepository;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.03.2017
 */
@Component
@Scope("application")
public class AuditLogService {
    @Autowired
    private AuditLogRepository auditLogRepository;

    public void saveAuditLog(AuditLog log){
        auditLogRepository.save(log);
    }
}
