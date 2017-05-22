package at.qe.sepm.asn_app.ui.controllers;

import at.qe.sepm.asn_app.repositories.AuditLogRepository;
import at.qe.sepm.asn_app.repositories.LunchRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Lukas Aukenthaler on 22.05.2017.
 */
public class LunchController {
    @Autowired
    private LunchRepository lunchRepository;

    @Autowired
    private AuditLogRepository auditLogRepository;




}
