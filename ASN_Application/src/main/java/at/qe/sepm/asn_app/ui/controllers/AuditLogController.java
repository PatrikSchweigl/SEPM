package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.models.nursery.AuditLog;
import at.qe.sepm.asn_app.repositories.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Emanuel Striednig <emanuel.striednig@student.uibk.ac.at>
 * on 27.03.2017.
 */
@Component
@Scope("view")
public class AuditLogController {

    @Autowired
    private AuditLogRepository auditLogRepository;

    /**
     * Returns a list of all users.
     *
     * @return
     */
    public Collection<AuditLog> getAuditLogs() {
        Collection<AuditLog> list = auditLogRepository.findAll();
        Collections.reverse((List<?>) list);
        return list;
    }

}
